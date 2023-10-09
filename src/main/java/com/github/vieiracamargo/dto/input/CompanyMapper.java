package com.github.vieiracamargo.dto.input;

import com.github.vieiracamargo.entities.Address;
import com.github.vieiracamargo.entities.CnpjNumber;
import com.github.vieiracamargo.entities.Company;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompanyMapper {
    public Company mapToCompany(CompanyInput registration) {
        Address address = mapToAddress(registration.address());
        CnpjNumber cnpjNumber = mapToCnpjNumber(registration.cpnj());
        return new Company(
                registration.name(),
                cnpjNumber,
                address,
                registration.phone(),
                registration.numberOfMotocyclesSpaces(),
                registration.numberOfCarSpaces()
        );
    }

    private Address mapToAddress(AddressInput addressInput) {
        return new Address(
                addressInput.street(),
                addressInput.city(),
                addressInput.state(),
                addressInput.country(),
                addressInput.zipCode(),
                addressInput.neighborhood(),
                addressInput.complement()
        );
    }

    private CnpjNumber mapToCnpjNumber(CnpjNumberInput cnpjNumberInput){
        return new CnpjNumber(
                cnpjNumberInput.number()
        );
    }
}
