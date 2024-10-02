package com.example.CouponCampaigner.controllers;

import com.example.CouponCampaigner.models.Campaign;
import com.example.CouponCampaigner.models.Company;
import com.example.CouponCampaigner.repositories.CampaignRepository;
import com.example.CouponCampaigner.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/AllCampaigns") // Get all campaigns
    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    @GetMapping("/Campaign/{id}") // Get campaign by ID
    public Campaign getCampaign(@PathVariable("id") Long id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        return campaign.orElse(null);
    }

    @PostMapping("/createCampaign") // Create a new campaign
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        Optional<Company> company = companyRepository.findById(campaign.getCompanyNit());
        if (company.isPresent()) {
            campaign.setCompany(company.get());
            return campaignRepository.save(campaign);
        } else {
            throw new RuntimeException("Company not found");
        }
    }

    @PutMapping("/updateCampaign/{id}") // Update an existing campaign
    public Campaign updateCampaign(@PathVariable("id") Long id, @RequestBody Campaign campaignDetails) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (campaign.isPresent()) {
            Campaign updatedCampaign = campaign.get();
            updatedCampaign.setName(campaignDetails.getName());
            updatedCampaign.setStartDate(campaignDetails.getStartDate());
            updatedCampaign.setEndDate(campaignDetails.getEndDate());

            Optional<Company> company = companyRepository.findById(campaignDetails.getCompanyNit());
            if (company.isPresent()) {
                updatedCampaign.setCompany(company.get());
            } else {
                throw new RuntimeException("Company not found");
            }

            return campaignRepository.save(updatedCampaign);
        } else {
            return null;
        }
    }

    @DeleteMapping("/deleteCampaign/{id}") // Delete a campaign
    public String deleteCampaign(@PathVariable("id") Long id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (campaign.isPresent()) {
            campaignRepository.delete(campaign.get());
            return "Campaign deleted successfully";
        } else {
            return "Campaign not found";
        }
    }
}