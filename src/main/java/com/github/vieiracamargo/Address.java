package com.github.vieiracamargo;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "zipCode", nullable = false)
    private String zipCode;
    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;
    @Column(name = "complement")
    private String complement;

    public Address() {
    }

    public Address(String street, String city, String state, String country, String zipCode, String neighborhood, String complement) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.neighborhood = neighborhood;
        this.complement = complement;
    }
}
