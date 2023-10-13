package com.github.vieiracamargo.dto.input;

import com.github.vieiracamargo.entities.Address;
import com.github.vieiracamargo.entities.Company;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompanyMapper {
    public Company mapToCompany(CompanyInput registration) {
        Address address = mapToAddress(registration.address());
        return new Company(
                registration.name(),
                registration.cpnj(),
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
}
