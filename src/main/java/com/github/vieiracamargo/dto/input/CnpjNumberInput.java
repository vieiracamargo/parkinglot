package com.github.vieiracamargo.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record CnpjNumberInput(
        @NotBlank
        @CNPJ
        @JsonProperty("number")
        String number

) {

}
