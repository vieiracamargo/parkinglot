package com.github.vieiracamargo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CNPJ {

    @Column(name = "cnpj")
    private String number;

    public CNPJ() {

    }
    public CNPJ(String number) {
        this.number = number;
    }


}
