package com.github.vieiracamargo.dto.output;

public record AddressOutput(
        String street,

        String city,

        String state,

        String country,

        String zipCode,
        String neighborhood,

        String complement
) {
}
