package com.github.vieiracamargo.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CompanyInput(
        @NotBlank
        @JsonProperty("name")
        String name,

        @Valid
        CnpjNumberInput cpnj,
        @NotNull
        @Valid
        @JsonProperty("address")
        AddressInput address,
        @NotBlank
        @JsonProperty("myphone")
        String phone,
        @NotNull
        @JsonProperty("number_of_Motocycles_Spaces")
        Integer numberOfMotocyclesSpaces,
        @NotNull
        @JsonProperty("number_of_Cars_Spaces")
        Integer numberOfCarSpaces
) {

}
