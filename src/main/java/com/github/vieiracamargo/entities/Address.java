package com.github.vieiracamargo.entities;

import jakarta.persistence.*;

import java.util.Objects;

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

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(country, address.country) && Objects.equals(zipCode, address.zipCode) && Objects.equals(neighborhood, address.neighborhood) && Objects.equals(complement, address.complement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, state, country, zipCode, neighborhood, complement);
    }
}
