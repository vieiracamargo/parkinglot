package com.github.vieiracamargo;

import com.github.vieiracamargo.entities.Address;
import com.github.vieiracamargo.entities.CnpjNumber;
import com.github.vieiracamargo.entities.Company;
import com.github.vieiracamargo.repository.CompanyRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
@QuarkusTest
class CompanyRepositoryTest {
    @Inject
    CompanyRepository companyRepository;
    Company company;
    @BeforeEach
    void setup(){
        CnpjNumber cnpjNumber = new CnpjNumber("66261814000198");
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
                "Joselu IT",
                cnpjNumber,
                address,
                "(95)97546-8754",
                10,
                20
        );
    }
    @Test
    @Transactional
    void shouldPersistCompany(){
        companyRepository.persist(company);
        Company actual = companyRepository.findById(company.getId());

        Assertions.assertNotNull(actual.getId());
    }
}