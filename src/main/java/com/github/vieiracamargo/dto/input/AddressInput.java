package com.github.vieiracamargo.dto.input;

import jakarta.validation.constraints.NotBlank;

public record AddressInput(
        @NotBlank
        String street,

        @NotBlank
        String city,

        @NotBlank
        String state,

        @NotBlank
        String country,

        @NotBlank
        String zipCode,
        @NotBlank
        String neighborhood,

        @NotBlank
        String complement
) {
}
