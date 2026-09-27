package com.resqhub.resqhub.model;

import org.springframework.stereotype.Component;

@Component
public class EmergencyResource {

    private int availableAmbulances = 5; // ধরি প্রাথমিকে ৫টি অ্যাম্বুলেন্স আছে

    // Thread-safe reservation method
    public synchronized boolean reserveAmbulance(String requestedBy) {
        if (availableAmbulances > 0) {
            availableAmbulances--;
            System.out.println("[CONCURRENCY CHECK] Ambulance reserved by: " + requestedBy + 
                               " | Remaining Ambulances: " + availableAmbulances);
            return true;
        } else {
            System.out.println("[CONCURRENCY CHECK] Reservation FAILED for: " + requestedBy + 
                               " | No ambulances available!");
            return false;
        }
    }

    public synchronized int getAvailableAmbulances() {
        return availableAmbulances;
    }
}