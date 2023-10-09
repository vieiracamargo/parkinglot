package com.github.vieiracamargo.resource;

import com.github.vieiracamargo.dto.input.AddressInput;
import com.github.vieiracamargo.dto.input.CnpjNumberInput;
import com.github.vieiracamargo.dto.input.CompanyInput;
import com.github.vieiracamargo.dto.output.AddressOutput;
import com.github.vieiracamargo.dto.output.CnpjNumberOutput;
import com.github.vieiracamargo.dto.output.CompanyOutput;
import com.github.vieiracamargo.service.CompanyService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URI;

@QuarkusTest
class CompanyResourceTest {

    @Inject
    CompanyResource companyResource;

    @InjectMock
    UriInfo uriInfo;
    @InjectMock
    CompanyService companyService;

    CompanyInput input;


    @BeforeEach
    void setup() {
        CnpjNumberInput cnpjNumber = new CnpjNumberInput("66261814000198");
        AddressInput addressInput = new AddressInput(
                "Dos matagais",
                "Goiânia",
                "Goiás",
                "Brasil",
                "74597010",
                "Residencial dos Ipês",
                "Qd.13 Lt.34"
        );

        input = new CompanyInput(
                "Joselu IT",
                cnpjNumber,
                addressInput,
                "(95)97546-8754",
                10,
                20
        );
    }
    @Test
    void test_registration_with_null_input_values() {
        CompanyInput registration = new CompanyInput(
                null,
                null,
                null,
                null,
                null,
                null
        );

        Assertions.assertThrows(ConstraintViolationException.class,
                () ->  companyResource.registerCompany(registration, uriInfo));
    }

    @Test
    void test_registration_with_empty_input_values() {
        CompanyInput registration = new CompanyInput(
                "",
                new CnpjNumberInput(""),
                new AddressInput("", "", "", "", "", "", ""),
                "",
                null,
                null
        );

        Assertions.assertThrows(ConstraintViolationException.class,
                () -> companyResource.registerCompany(registration, uriInfo));
    }
    @Test
    void test_successful_registration() {
        CnpjNumberOutput cnpjNumberOutput = new CnpjNumberOutput("66261814000198");
        AddressOutput addressOutput = new AddressOutput(
                "Dos matagais",
                "Goiânia",
                "Goiás",
                "Brasil",
                "74597010",
                "Residencial dos Ipês",
                "Qd.13 Lt.34"
        );

        CompanyOutput expected = new CompanyOutput(
                1L,
                "Joselu IT",
                cnpjNumberOutput,
                addressOutput,
                "(95)97546-8754",
                10,
                20
        );

        URI uri = URI.create("http://example.com/company");
        Mockito.when(uriInfo.getAbsolutePathBuilder()).thenReturn(UriBuilder.fromUri(uri));
        Mockito.when(companyService.registerCompany(Mockito.any(CompanyInput.class))).thenReturn(expected);

        String expectecdUri = "http://example.com/company/%d".formatted(expected.id());
        Response response = companyResource.registerCompany(input, uriInfo);

        Assertions.assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        Assertions.assertEquals(expectecdUri, response.getLocation().toString());
        Assertions.assertEquals(CompanyOutput.class, response.getEntity().getClass());
        Assertions.assertEquals(expected, response.getEntity());
    }
}