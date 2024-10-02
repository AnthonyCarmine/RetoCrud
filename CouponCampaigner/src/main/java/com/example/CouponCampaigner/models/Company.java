package com.example.CouponCampaigner.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @Column(name = "nit", length = 50)
    private String nit;

    @Column(name = "name", length = 255)
    private String name;

    // Getters and Setters
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}