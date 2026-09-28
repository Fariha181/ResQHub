package com.resqhub.resqhub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hospitals")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String location;
    private int availableIcuBeds;
    private int availableGeneralBeds;

    public Hospital() {}

    public Hospital(String name, String email, String password, String location, int availableIcuBeds, int availableGeneralBeds) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
        this.availableIcuBeds = availableIcuBeds;
        this.availableGeneralBeds = availableGeneralBeds;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getAvailableIcuBeds() { return availableIcuBeds; }
    public void setAvailableIcuBeds(int availableIcuBeds) { this.availableIcuBeds = availableIcuBeds; }

    public int getAvailableGeneralBeds() { return availableGeneralBeds; }
    public void setAvailableGeneralBeds(int availableGeneralBeds) { this.availableGeneralBeds = availableGeneralBeds; }
}