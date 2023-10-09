package com.github.vieiracamargo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class CnpjNumber {

    @Column(name = "cnpj")
    private String number;

    public CnpjNumber() {

    }
    public CnpjNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CnpjNumber cnpjNumber = (CnpjNumber) o;
        return Objects.equals(number, cnpjNumber.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
