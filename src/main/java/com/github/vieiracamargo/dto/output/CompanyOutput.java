package com.github.vieiracamargo.dto.output;

public record CompanyOutput(

        Long id,
        String name,
        String cnpjNumber,
        AddressOutput address,
        String phone,
        Integer numberOfMotocyclesSpaces,
        Integer numberOfCarSpaces

) {
}
