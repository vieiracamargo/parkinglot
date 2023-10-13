package com.github.vieiracamargo.dto.input;

import com.github.vieiracamargo.dto.output.AddressOutput;
import com.github.vieiracamargo.dto.output.CompanyOutput;
import com.github.vieiracamargo.entities.Address;
import com.github.vieiracamargo.entities.Company;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompanyMapper {
    public Company toCompany(CompanyInput registration) {
        Address address = toAddress(registration.address());
        return new Company(
                registration.name(),
                registration.cpnj(),
                address,
                registration.phone(),
                registration.numberOfMotocyclesSpaces(),
                registration.numberOfCarSpaces()
        );
    }

    private Address toAddress(AddressInput addressInput) {
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

    public CompanyOutput toCompanyOutput(Company company) {
        AddressOutput addressOutput = toAddressOutput(company.getAddress());

        return new CompanyOutput(
                company.getId(),
                company.getName(),
                company.getCnpj(),
                addressOutput,
                company.getPhone(),
                company.getNumberOfMotocyclesSpaces(),
                company.getNumberOfCarSpaces()
        );
    }

    private AddressOutput toAddressOutput(Address address) {
        return new AddressOutput(
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getZipCode(),
                address.getNeighborhood(),
                address.getComplement()
        );
    }
}
