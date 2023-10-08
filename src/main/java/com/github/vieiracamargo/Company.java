package com.github.vieiracamargo;

import jakarta.persistence.*;

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
    private CNPJ cnpj;

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

    public Company(String name, CNPJ cnpj, Address address, String phone, Integer numberOfMotocyclesSpaces, Integer numberOfCarSpaces) {
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.phone = phone;
        this.numberOfMotocyclesSpaces = numberOfMotocyclesSpaces;
        this.numberOfCarSpaces = numberOfCarSpaces;
    }
}
