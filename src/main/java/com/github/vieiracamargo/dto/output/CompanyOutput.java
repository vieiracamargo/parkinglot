package com.github.vieiracamargo.dto.output;

public record CompanyOutput(

        Long id,
        String name,
        CnpjNumberOutput cnpjNumber,
        AddressOutput address,
        String phone,
        Integer numberOfMotocyclesSpaces,
        Integer numberOfCarSpaces

) {
}
