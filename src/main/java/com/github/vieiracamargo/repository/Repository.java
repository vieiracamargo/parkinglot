package com.github.vieiracamargo.repository;

import com.github.vieiracamargo.entities.Company;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface Repository extends PanacheRepository<Company> {
}
