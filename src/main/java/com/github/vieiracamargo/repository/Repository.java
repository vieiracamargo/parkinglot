package com.github.vieiracamargo.repository;

import com.github.vieiracamargo.entities.Company;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.Optional;

public interface Repository extends PanacheRepository<Company> {

    Optional<Company> findByCnpjOptional(String cnpj);
}
