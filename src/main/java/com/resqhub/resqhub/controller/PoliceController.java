package com.resqhub.resqhub.controller;

import com.resqhub.resqhub.model.PoliceStation;
import com.resqhub.resqhub.repository.PoliceStationRepository;
import com.resqhub.resqhub.singleton.SystemLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/police")
public class PoliceController {

    @Autowired
    private PoliceStationRepository policeRepository;

    // ১. নতুন পুলিশ স্টেশন এনলিস্ট করা
    @PostMapping("/add")
    public String addPoliceStation(@RequestBody PoliceStation station) {
        policeRepository.save(station);
        SystemLogger.getInstance().log("Police Station Enlisted: " + station.getStationName());
        return "Police Station added successfully!";
    }

    // ২. ইমার্জেন্সি SOS অ্যালার্ট পাঠানো (লোকেশন অনুযায়ী নিকটস্থ পুলিশকে অ্যালার্ট করা)
    @PostMapping("/sos-alert")
    public String sendPoliceSOS(@RequestParam String area, @RequestParam String description) {
        SystemLogger.getInstance().log("POLICE SOS ALERT Triggered at Area: " + area);
        
        List<PoliceStation> nearbyStations = policeRepository.findByAreaIgnoreCase(area);

        if (!nearbyStations.isEmpty()) {
            PoliceStation assignedStation = nearbyStations.get(0);
            return "POLICE SOS DISPATCHED: Alert sent to " + assignedStation.getStationName() + 
                   " (" + assignedStation.getArea() + "). Emergency Contact: " + assignedStation.getContactNumber() + 
                   ". Incident Details: " + description;
        }

        return "EMERGENCY WARNING: No nearby police station found for " + area + ". Alert forwarded to Central Police Control Room (999).";
    }

    // ৩. সব পুলিশ স্টেশনের তালিকা দেখা
    @GetMapping("/all")
    public List<PoliceStation> getAllStations() {
        return policeRepository.findAll();
    }
}
