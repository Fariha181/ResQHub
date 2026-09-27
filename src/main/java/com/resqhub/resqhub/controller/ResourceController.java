package com.resqhub.resqhub.controller;

import com.resqhub.resqhub.model.EmergencyResource;
import com.resqhub.resqhub.singleton.SystemLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private EmergencyResource emergencyResource;

    @PostMapping("/reserve")
    public String reserve(@RequestParam String requester) {
        SystemLogger.getInstance().log("Resource reservation request received from: " + requester);

        boolean success = emergencyResource.reserveAmbulance(requester);

        if (success) {
            return "SUCCESS: Ambulance allocated to " + requester + 
                   ". Remaining: " + emergencyResource.getAvailableAmbulances();
        } else {
            return "FAILED: Out of ambulances!";
        }
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Available Ambulances: " + emergencyResource.getAvailableAmbulances();
    }
}