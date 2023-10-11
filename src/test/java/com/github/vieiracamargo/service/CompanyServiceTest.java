package com.github.vieiracamargo.service;


import com.github.vieiracamargo.dto.input.AddressInput;
import com.github.vieiracamargo.dto.input.CnpjNumberInput;
import com.github.vieiracamargo.dto.input.CompanyInput;
import com.github.vieiracamargo.dto.input.CompanyMapper;
import com.github.vieiracamargo.dto.output.CompanyOutput;
import com.github.vieiracamargo.dto.output.CompanyOutputMapper;
import com.github.vieiracamargo.entities.Address;
import com.github.vieiracamargo.entities.CnpjNumber;
import com.github.vieiracamargo.entities.Company;
import com.github.vieiracamargo.exception.CompanyNotFoundException;
import com.github.vieiracamargo.repository.CompanyRepository;
import com.github.vieiracamargo.repository.Repository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        CnpjNumberInput cnpjNumber = new CnpjNumberInput("66261814000198");
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
                cnpjNumber,
                address,
                "(95)97546-8754",
                10,
                20
        );

        mapper = new CompanyMapper();
        companyOutputMapper = new CompanyOutputMapper();
        repository = Mockito.mock(CompanyRepository.class);
        companyService= new CompanyService(mapper, companyOutputMapper, repository);
    }

    @Test
    void test_company_successfully_registered() {
        Mockito.doNothing().when(repository).persist(Mockito.any(Company.class));
        CompanyOutput companyOutput = companyService.registerCompany(companyInput);

        Mockito.verify(repository, Mockito.times(1)).persist(mapper.mapToCompany(companyInput));
        Assertions.assertNotNull(companyOutput);
    }

    @Test
    void find_company_by_id_with_valid_id(){
        CnpjNumber cpnjOutput = new CnpjNumber("66261814000198");
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
                cpnjOutput,
                addressOutput,
                "(95)97546-8754",
                10,
                20
        );

        Mockito.when(repository.findByIdOptional(Mockito.any(Long.class))).thenReturn(Optional.of(output));
        CompanyOutput response = companyService.findCompanyById(1L);
        CompanyOutput expected = companyOutputMapper.mapToCompanyOutput(output);

        Assertions.assertEquals(expected, response);
    }
    @Test
    void throw_CompanyNotFoundException_if_is_invalid_id(){
        Mockito.when(repository.findByIdOptional(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(CompanyNotFoundException.class,
                () -> companyService.findCompanyById(0L)
        );
    }
}