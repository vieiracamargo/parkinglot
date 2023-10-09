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

    @Embedded
    @Column(name = "cnpj", nullable = false)
    private CnpjNumber cnpjNumber;

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

    public CnpjNumber getCnpjNumber() {
        return cnpjNumber;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getNumberOfMotocyclesSpaces() {
        return numberOfMotocyclesSpaces;
    }

    public Integer getNumberOfCarSpaces() {
        return numberOfCarSpaces;
    }

    public Company(String name, CnpjNumber cnpjNumber, Address address, String phone, Integer numberOfMotocyclesSpaces, Integer numberOfCarSpaces) {
        this.name = name;
        this.cnpjNumber = cnpjNumber;
        this.address = address;
        this.phone = phone;
        this.numberOfMotocyclesSpaces = numberOfMotocyclesSpaces;
        this.numberOfCarSpaces = numberOfCarSpaces;
    }

    public Company(Long id, String name, CnpjNumber cnpjNumber, Address address, String phone, Integer numberOfMotocyclesSpaces, Integer numberOfCarSpaces) {
        this.id = id;
        this.name = name;
        this.cnpjNumber = cnpjNumber;
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
        return Objects.equals(id, company.id) && Objects.equals(name, company.name) && Objects.equals(cnpjNumber, company.cnpjNumber) && Objects.equals(address, company.address) && Objects.equals(phone, company.phone) && Objects.equals(numberOfMotocyclesSpaces, company.numberOfMotocyclesSpaces) && Objects.equals(numberOfCarSpaces, company.numberOfCarSpaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cnpjNumber, address, phone, numberOfMotocyclesSpaces, numberOfCarSpaces);
    }
}

