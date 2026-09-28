package com.resqhub.resqhub.controller;

import com.resqhub.resqhub.model.Hospital;
import com.resqhub.resqhub.repository.HospitalRepository;
import com.resqhub.resqhub.singleton.SystemLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalRepository hospitalRepository;

    // ১. নতুন হাসপাতাল রেজিস্টার করা
    @PostMapping("/register")
    public String registerHospital(@RequestBody Hospital hospital) {
        hospitalRepository.save(hospital);
        SystemLogger.getInstance().log("New Hospital Registered: " + hospital.getName());
        return "Hospital registered successfully!";
    }

    // ২. হাসপাতাল লগইন
    @PostMapping("/login")
    public String loginHospital(@RequestParam String email, @RequestParam String password) {
        Optional<Hospital> hospitalOpt = hospitalRepository.findByEmail(email);

        if (hospitalOpt.isPresent() && hospitalOpt.get().getPassword().equals(password)) {
            SystemLogger.getInstance().log("Hospital Logged In: " + hospitalOpt.get().getName());
            return "LOGIN SUCCESS: Welcome " + hospitalOpt.get().getName();
        }
        return "LOGIN FAILED: Invalid credentials!";
    }

    // ৩. হাসপাতাল তাদের ফাকা বেড সংখ্যা আপডেট করবে
    @PutMapping("/update-beds/{id}")
    public String updateBeds(@PathVariable Long id, 
                             @RequestParam int icuBeds, 
                             @RequestParam int generalBeds) {
        Optional<Hospital> hospitalOpt = hospitalRepository.findById(id);

        if (hospitalOpt.isPresent()) {
            Hospital hospital = hospitalOpt.get();
            hospital.setAvailableIcuBeds(icuBeds);
            hospital.setAvailableGeneralBeds(generalBeds);
            hospitalRepository.save(hospital);

            SystemLogger.getInstance().log("Beds updated for: " + hospital.getName());
            return "Bed counts updated successfully for " + hospital.getName();
        }
        return "Hospital not found!";
    }

    // ৪. পাবলিক ভিউ: সব হাসপাতালের খালি বেডের তালিকা
    @GetMapping("/all")
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }
}