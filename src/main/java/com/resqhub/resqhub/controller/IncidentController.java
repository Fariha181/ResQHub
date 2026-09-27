package com.resqhub.resqhub.controller;

import com.resqhub.resqhub.factory.Responder;
import com.resqhub.resqhub.factory.ResponderFactory;
import com.resqhub.resqhub.model.Incident;
import com.resqhub.resqhub.observer.AgencyNotifier;
import com.resqhub.resqhub.repository.IncidentRepository;
import com.resqhub.resqhub.singleton.SystemLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private ResponderFactory responderFactory;

    @Autowired
    private AgencyNotifier agencyNotifier;

    @PostMapping
    public Incident createIncident(@RequestBody Incident incident) {
        // Singleton Pattern Call
        SystemLogger.getInstance().log("Creating new incident: " + incident.getTitle());

        // Factory Pattern Call
        Responder responder = responderFactory.getResponder(incident.getIncidentType());

        if (responder != null) {
            incident.setAssignedAgency(responder.getAgencyType());
        } else {
            incident.setAssignedAgency("GENERAL");
        }

        incident.setStatus("OPEN");
        Incident savedIncident = incidentRepository.save(incident);

        // Singleton Log
        SystemLogger.getInstance().log("Incident saved with ID: " + savedIncident.getId());

        // Observer Pattern Call
        agencyNotifier.onIncidentCreated(
            savedIncident.getTitle(), 
            savedIncident.getLocation(), 
            savedIncident.getAssignedAgency()
        );

        return savedIncident;
    }

    @GetMapping
    public List<Incident> getAllIncidents() {
        SystemLogger.getInstance().log("Fetching all incidents from database");
        return incidentRepository.findAll();
    }
}