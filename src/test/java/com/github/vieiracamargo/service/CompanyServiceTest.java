package com.github.vieiracamargo.service;


import com.github.vieiracamargo.dto.input.AddressInput;
import com.github.vieiracamargo.dto.input.CompanyInput;
import com.github.vieiracamargo.dto.input.CompanyMapper;
import com.github.vieiracamargo.dto.output.CompanyOutput;
import com.github.vieiracamargo.dto.output.CompanyOutputMapper;
import com.github.vieiracamargo.entities.Address;
import com.github.vieiracamargo.entities.Company;
import com.github.vieiracamargo.exception.CompanyNotFoundException;
import com.github.vieiracamargo.repository.CompanyRepository;
import com.github.vieiracamargo.repository.Repository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@QuarkusTest
class CompanyServiceTest {

    CompanyMapper mapper;
    CompanyOutputMapper companyOutputMapper;
    Repository repository;
    CompanyService companyService;
    CompanyInput companyInput;

    @BeforeEach
    void setup() {
        AddressInput address = new AddressInput(
                "Dos matagais",
                "Goiânia",
                "Goiás",
                "Brasil",
                "74597010",
                "Residencial dos Ipês",
                "Qd.13 Lt.34"
        );

        companyInput = new CompanyInput(
                "Joselu IT",
                "66261814000198",
                address,
                "(95)97546-8754",
                10,
                20
        );

        mapper = new CompanyMapper();
        companyOutputMapper = new CompanyOutputMapper();
        repository = Mockito.mock(CompanyRepository.class);
        companyService = new CompanyService(mapper, companyOutputMapper, repository);
    }

    @Test
    void test_company_successfully_registered() {
        // When
        Mockito.doNothing().when(repository).persist(Mockito.any(Company.class));
        CompanyOutput companyOutput = companyService.registerCompany(companyInput);
        // Then
        Mockito.verify(repository, Mockito.times(1)).persist(mapper.mapToCompany(companyInput));
        Assertions.assertNotNull(companyOutput);
    }

    @Test
    void find_company_by_id_with_valid_id() {
        //Given
        Address addressOutput = new Address(
                "Dos matagais",
                "Goiânia",
                "Goiás",
                "Brasil",
                "74597010",
                "Residencial dos Ipês",
                "Qd.13 Lt.34"
        );

        Company output = new Company(
                1L,
                "Joselu IT",
                "66261814000198",
                addressOutput,
                "(95)97546-8754",
                10,
                20
        );
        // When
        Mockito.when(repository.findByIdOptional(Mockito.any(Long.class))).thenReturn(Optional.of(output));
        CompanyOutput response = companyService.findCompanyById(1L);
        CompanyOutput expected = companyOutputMapper.mapToCompanyOutput(output);
        // Then
        Assertions.assertEquals(expected, response);
    }

    @Test
    void throw_CompanyNotFoundException_if_is_invalid_id() {
        // When
        Mockito.when(repository.findByIdOptional(Mockito.any())).thenReturn(Optional.empty());
        // Then
        Assertions.assertThrows(CompanyNotFoundException.class,
                () -> companyService.findCompanyById(0L)
        );
    }

    @Test
    void should_return_all_companies() {
        // Given
        Address address = new Address("Dos matagais", "Goiânia", "Goiás", "Brasil", "74597010", "Residencial dos Ipês", "Qd.13 Lt.34");
        Company company = new Company(1L, "Joselu IT", "66261814000198", address, "(95)97546-8754", 10, 20);
        int start = 0;
        int size = 10;
        String column = "id";
        Sort.Direction direction = Sort.Direction.Ascending;
        PanacheQuery<Company> query = Mockito.mock(PanacheQuery.class);
        List<Company> companies = List.of(company);
        List<CompanyOutput> expected = companies
                .stream()
                .map(c -> companyOutputMapper.mapToCompanyOutput(c))
                .toList();
        // When
        Mockito.when(repository.findAll(Mockito.any(Sort.class))).thenReturn(query);
        Mockito.when(query.page(Mockito.any(Page.class))).thenReturn(query);
        Mockito.when(query.list()).thenReturn(companies);
        List<CompanyOutput> actual = companyService.findAll(start, size, column, direction);
        // Then
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get(0));
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void no_companies_found() {
        // Given
        int startPage = 0;
        int size = 10;
        String column = "id";
        Sort.Direction direction = Sort.Direction.Ascending;
        PanacheQuery<Company> query = Mockito.mock(PanacheQuery.class);
        // When
        Mockito.when(repository.findAll(Mockito.any(Sort.class))).thenReturn(query);
        Mockito.when(query.page(Mockito.any(Page.class))).thenReturn(query);
        Mockito.when(query.list()).thenReturn(Collections.emptyList());
        List<CompanyOutput> actual = companyService.findAll(startPage, size, column, direction);
        // Then
        Assertions.assertNotNull(actual);
        Assertions.assertTrue(actual.isEmpty());
    }
}