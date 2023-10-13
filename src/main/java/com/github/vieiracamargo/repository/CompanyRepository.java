package com.github.vieiracamargo.repository;

import com.github.vieiracamargo.entities.Company;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CompanyRepository implements Repository {
    @Override
    public Optional<Company> findByCnpjOptional(String cnpj) {
        return find("cnpj", cnpj).firstResultOptional();
    }
}
