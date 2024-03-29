package com.github.vieiracamargo.service;

import com.github.vieiracamargo.dto.input.CompanyInput;
import com.github.vieiracamargo.dto.input.CompanyMapper;
import com.github.vieiracamargo.dto.output.CompanyOutput;
import com.github.vieiracamargo.entities.Company;
import com.github.vieiracamargo.exception.CompanyAlreadyExistsException;
import com.github.vieiracamargo.exception.CompanyNotFoundException;
import com.github.vieiracamargo.repository.Repository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CompanyService {
    private final CompanyMapper mapper;
    private final Repository repository;

    public CompanyService(CompanyMapper mapper, Repository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional
    public CompanyOutput registerCompany(CompanyInput registration) {
        Company company = mapper.toCompany(registration);
        if (companyAlreadyExists(company)) {
            throw new CompanyAlreadyExistsException("Company with this CPNJ already exists");
        }

        repository.persist(company);
        return mapper.toCompanyOutput(company);
    }

    public CompanyOutput findCompanyById(Long companyId) {
        Company response = getCompanyByid(companyId);
        return mapper.toCompanyOutput(response);
    }

    public List<CompanyOutput> findAll(int startPage, int size, String column, Sort.Direction direction) {
        Page page = Page.of(startPage, size);
        Sort sort = Sort.by(column, direction);

        return repository
                .findAll(sort)
                .page(page)
                .list()
                .stream()
                .map(mapper::toCompanyOutput)
                .toList();
    }

    @Transactional
    public CompanyOutput updateCompany(Long companyId, CompanyInput update) {
        Company existingCompany = getCompanyByid(companyId);
        Company updatedCompany = updateCompanyData(existingCompany, update);
        repository.persist(updatedCompany);
        return mapper.toCompanyOutput(updatedCompany);
    }

    private boolean companyAlreadyExists(Company company) {
        return repository.findByCnpjOptional(company.getCnpj()).isPresent();
    }

    private Company getCompanyByid(Long companyId) {
        return repository
                .findByIdOptional(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Could not find company with id " + companyId));
    }

    private Company updateCompanyData(Company existingCompany, CompanyInput update) {
        Company updateData = mapper.toCompany(update);
        existingCompany.setName(updateData.getName());
        existingCompany.setCnpj(updateData.getCnpj());
        existingCompany.setAddress(updateData.getAddress());
        existingCompany.setPhone(updateData.getPhone());
        existingCompany.setNumberOfMotocyclesSpaces(updateData.getNumberOfMotocyclesSpaces());
        existingCompany.setNumberOfMotocyclesSpaces(update.numberOfCarSpaces());
        return existingCompany;
    }
}
