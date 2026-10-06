package com.resqhub.resqhub.service;

import com.resqhub.resqhub.dto.IncidentRequest;
import com.resqhub.resqhub.dto.IncidentResponse;
import com.resqhub.resqhub.factory.Responder;
import com.resqhub.resqhub.factory.ResponderFactory;
import com.resqhub.resqhub.model.Incident;
import com.resqhub.resqhub.observer.AgencyNotifier;
import com.resqhub.resqhub.repository.IncidentRepository;
import com.resqhub.resqhub.singleton.SystemLogger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {
    private final IncidentRepository incidentRepository;
    private final ResponderFactory responderFactory;
    private final AgencyNotifier agencyNotifier;

    public IncidentService(IncidentRepository incidentRepository,
                           ResponderFactory responderFactory,
                           AgencyNotifier agencyNotifier) {
        this.incidentRepository = incidentRepository;
        this.responderFactory = responderFactory;
        this.agencyNotifier = agencyNotifier;
    }

    public IncidentResponse createIncident(IncidentRequest request) {
        SystemLogger.getInstance().log("Creating new incident: " + request.getTitle());

        Incident incident = new Incident();
        incident.setTitle(request.getTitle().trim());
        incident.setDescription(request.getDescription().trim());
        incident.setLocation(request.getLocation().trim());
        incident.setIncidentType(request.getIncidentType().trim());

        Responder responder = responderFactory.getResponder(incident.getIncidentType());
        incident.setAssignedAgency(responder == null ? "GENERAL" : responder.getAgencyType());
        incident.setStatus("OPEN");

        Incident savedIncident = incidentRepository.save(incident);
        SystemLogger.getInstance().log("Incident saved with ID: " + savedIncident.getId());

        agencyNotifier.onIncidentCreated(
                savedIncident.getTitle(),
                savedIncident.getLocation(),
                savedIncident.getAssignedAgency()
        );

        return IncidentResponse.from(savedIncident);
    }

    public List<Incident> getAllIncidents() {
        SystemLogger.getInstance().log("Fetching all incidents from database");
        return incidentRepository.findAll();
    }
}
