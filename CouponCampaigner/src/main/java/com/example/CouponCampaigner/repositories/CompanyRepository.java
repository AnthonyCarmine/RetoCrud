package com.example.CouponCampaigner.repositories;


import com.example.CouponCampaigner.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;



public interface CompanyRepository extends JpaRepository<Company, String> {
    Optional<Company> findByName(String name);
}
