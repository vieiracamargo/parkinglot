package com.github.vieiracamargo.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "motocycles_spaces", nullable = false)
    private Integer numberOfMotocyclesSpaces;

    @Column(name = "cars_spaces", nullable = false)
    private Integer numberOfCarSpaces;


    public Company() {

    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNumberOfMotocyclesSpaces(Integer numberOfMotocyclesSpaces) {
        this.numberOfMotocyclesSpaces = numberOfMotocyclesSpaces;
    }

    public void setNumberOfCarSpaces(Integer numberOfCarSpaces) {
        this.numberOfCarSpaces = numberOfCarSpaces;
    }

    public Integer getNumberOfMotocyclesSpaces() {
        return numberOfMotocyclesSpaces;
    }

    public Integer getNumberOfCarSpaces() {
        return numberOfCarSpaces;
    }

    public Company(Long id, String name, String cnpj, Address address, String phone, Integer numberOfMotocyclesSpaces, Integer numberOfCarSpaces) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.phone = phone;
        this.numberOfMotocyclesSpaces = numberOfMotocyclesSpaces;
        this.numberOfCarSpaces = numberOfCarSpaces;
    }

    public Company(String name, String cnpj, Address address, String phone, Integer numberOfMotocyclesSpaces, Integer numberOfCarSpaces) {
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.phone = phone;
        this.numberOfMotocyclesSpaces = numberOfMotocyclesSpaces;
        this.numberOfCarSpaces = numberOfCarSpaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(name, company.name) && Objects.equals(cnpj, company.cnpj) && Objects.equals(address, company.address) && Objects.equals(phone, company.phone) && Objects.equals(numberOfMotocyclesSpaces, company.numberOfMotocyclesSpaces) && Objects.equals(numberOfCarSpaces, company.numberOfCarSpaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cnpj, address, phone, numberOfMotocyclesSpaces, numberOfCarSpaces);
    }
}

