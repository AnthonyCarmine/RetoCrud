package com.example.CouponCampaigner.controllers;

import com.example.CouponCampaigner.models.Company;
import com.example.CouponCampaigner.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/AllCompanies") // Get all companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/Company/{identifier}") // Get company by NIT or name
    public Company getCompany(@PathVariable("identifier") String identifier) {
        Optional<Company> company;

        // Verificamos si el identificador es un NIT
        if (identifier.matches("\\d+")) {
            company = companyRepository.findById(identifier);
        } else {
            company = companyRepository.findByName(identifier);
        }

        return company.orElse(null);
    }
}

