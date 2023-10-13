package com.github.vieiracamargo.dto.output;

import com.github.vieiracamargo.entities.Address;
import com.github.vieiracamargo.entities.Company;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompanyOutputMapper {
    public CompanyOutput mapToCompanyOutput(Company company) {
        AddressOutput addressOutput = mapToAddressOutput(company.getAddress());

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

    private AddressOutput mapToAddressOutput(Address address) {
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
