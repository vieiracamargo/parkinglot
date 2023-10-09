package com.github.vieiracamargo.service;

import com.github.vieiracamargo.dto.input.CompanyInput;
import com.github.vieiracamargo.dto.input.CompanyMapper;
import com.github.vieiracamargo.dto.output.CompanyOutput;
import com.github.vieiracamargo.dto.output.CompanyOutputMapper;
import com.github.vieiracamargo.entities.Company;
import com.github.vieiracamargo.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CompanyService {
    @Inject
    CompanyMapper companyMapper;

    @Inject
    CompanyOutputMapper companyOutputMapper;
    @Inject
    Repository repository;



    @Transactional
    public CompanyOutput registerCompany(CompanyInput registration) {
        Company company = companyMapper.mapToCompany(registration);
        repository.persist(company);
        return companyOutputMapper.mapToCompanyOutput(company);
    }

}
