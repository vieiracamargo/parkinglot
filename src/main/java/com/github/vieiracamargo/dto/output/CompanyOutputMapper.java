package com.github.vieiracamargo.dto.output;

import com.github.vieiracamargo.entities.Address;
import com.github.vieiracamargo.entities.CnpjNumber;
import com.github.vieiracamargo.entities.Company;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompanyOutputMapper {
    public CompanyOutput mapToCompanyOutput(Company company) {
        AddressOutput addressOutput = mapToAddressOutput(company.getAddress());
        CnpjNumberOutput cnpjNumberOutput = mapToCnpjNumberOutput(company.getCnpjNumber());

        return new CompanyOutput(
                company.getId(),
                company.getName(),
                cnpjNumberOutput,
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

    private CnpjNumberOutput mapToCnpjNumberOutput(CnpjNumber cnpjNumber) {
        return new CnpjNumberOutput(
                cnpjNumber.getNumber()
        );
    }

}
