package com.github.vieiracamargo.service;

import com.github.vieiracamargo.dto.input.CompanyInput;
import com.github.vieiracamargo.dto.input.CompanyMapper;
import com.github.vieiracamargo.dto.output.CompanyOutput;
import com.github.vieiracamargo.dto.output.CompanyOutputMapper;
import com.github.vieiracamargo.entities.Company;
import com.github.vieiracamargo.exception.CompanyNotFoundException;
import com.github.vieiracamargo.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CompanyService {
    private final CompanyMapper companyMapper;

    private final CompanyOutputMapper companyOutputMapper;

    private final Repository repository;

    public CompanyService(CompanyMapper companyMapper, CompanyOutputMapper companyOutputMapper, Repository repository) {
        this.companyMapper = companyMapper;
        this.companyOutputMapper = companyOutputMapper;
        this.repository = repository;
    }

    @Transactional
    public CompanyOutput registerCompany(CompanyInput registration) {
        Company company = companyMapper.mapToCompany(registration);
        repository.persist(company);
        return companyOutputMapper.mapToCompanyOutput(company);
    }

    public CompanyOutput findCompanyById(Long companyId) {
        Company response = repository
                .findByIdOptional(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Could not find company with id " + companyId));

        return companyOutputMapper.mapToCompanyOutput(response);
    }

}
