package com.github.vieiracamargo;


import com.github.vieiracamargo.dto.input.AddressInput;
import com.github.vieiracamargo.dto.input.CnpjNumberInput;
import com.github.vieiracamargo.dto.input.CompanyInput;
import com.github.vieiracamargo.dto.input.CompanyMapper;
import com.github.vieiracamargo.dto.output.CompanyOutput;
import com.github.vieiracamargo.entities.Address;
import com.github.vieiracamargo.entities.CnpjNumber;
import com.github.vieiracamargo.entities.Company;
import com.github.vieiracamargo.repository.Repository;
import com.github.vieiracamargo.service.CompanyService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class CompanyServiceTest {

    @Inject
    CompanyMapper mapper;

    @Inject
    CompanyService companyService;

    @InjectMock
    Repository repository;

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
    }

    @Test
    void test_company_successfully_registered() {
        Mockito.doNothing().when(repository).persist(Mockito.any(Company.class));
        CompanyOutput companyOutput = companyService.registerCompany(companyInput);

        Mockito.verify(repository, Mockito.times(1)).persist(mapper.mapToCompany(companyInput));
        Assertions.assertNotNull(companyOutput);
    }

}