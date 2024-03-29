package com.github.vieiracamargo.service;


import com.github.vieiracamargo.dto.input.AddressInput;
import com.github.vieiracamargo.dto.input.CompanyInput;
import com.github.vieiracamargo.dto.input.CompanyMapper;
import com.github.vieiracamargo.dto.output.CompanyOutput;
import com.github.vieiracamargo.entities.Address;
import com.github.vieiracamargo.entities.Company;
import com.github.vieiracamargo.exception.CompanyAlreadyExistsException;
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
    Repository repository;
    CompanyService companyService;
    CompanyInput companyInput;
    Company company;

    @BeforeEach
    void setup() {
        AddressInput addressInput = new AddressInput(
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
                addressInput,
                "(95)97546-8754",
                10,
                20
        );

        Address address = new Address(
                "Dos matagais",
                "Goiânia",
                "Goiás",
                "Brasil",
                "74597010",
                "Residencial dos Ipês",
                "Qd.13 Lt.34"
        );

        company = new Company(
                1L,
                "Joselu IT",
                "66261814000198",
                address,
                "(95)97546-8754",
                10,
                20
        );

        mapper = new CompanyMapper();
        repository = Mockito.mock(CompanyRepository.class);
        companyService = new CompanyService(mapper, repository);
    }

    @Test
    void should_throw_company_already_exists_exception_if_cnpj_already_registered() {
        // Given
        Optional<Company> expected = Optional.of(company);
        // When
        Mockito.when(repository.findByCnpjOptional(Mockito.any(String.class))).thenReturn(expected);
        // Then
        Assertions.assertThrows(CompanyAlreadyExistsException.class,
                () -> companyService.registerCompany(companyInput)
        );
    }

    @Test
    void should_successfully_register_a_company() {
        // When
        Mockito.doNothing().when(repository).persist(Mockito.any(Company.class));
        CompanyOutput companyOutput = companyService.registerCompany(companyInput);
        // Then
        Mockito.verify(repository, Mockito.times(1)).persist(mapper.toCompany(companyInput));
        Assertions.assertNotNull(companyOutput);
    }

    @Test
    void should_find_company_when_given_valid_id() {
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
        CompanyOutput expected = mapper.toCompanyOutput(output);
        // Then
        Assertions.assertEquals(expected, response);
    }

    @Test
    void should_throw_company_not_found_exception_if_is_invalid_id() {
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
                .map(c -> mapper.toCompanyOutput(c))
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
    void should_return_empty_list_when_there_is_no_registered_companies() {
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