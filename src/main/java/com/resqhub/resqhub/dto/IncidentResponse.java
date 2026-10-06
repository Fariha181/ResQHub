package com.resqhub.resqhub.dto;

import com.resqhub.resqhub.model.Incident;

public class IncidentResponse {
    private final Long id;
    private final String title;
    private final String incidentType;
    private final String description;
    private final String location;
    private final String status;
    private final String assignedAgency;

    private IncidentResponse(Long id, String title, String incidentType, String description,
                             String location, String status, String assignedAgency) {
        this.id = id;
        this.title = title;
        this.incidentType = incidentType;
        this.description = description;
        this.location = location;
        this.status = status;
        this.assignedAgency = assignedAgency;
    }

    public static IncidentResponse from(Incident incident) {
        return new IncidentResponse(
                incident.getId(),
                incident.getTitle(),
                incident.getIncidentType(),
                incident.getDescription(),
                incident.getLocation(),
                incident.getStatus(),
                incident.getAssignedAgency()
        );
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getIncidentType() { return incidentType; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }
    public String getAssignedAgency() { return assignedAgency; }
}
