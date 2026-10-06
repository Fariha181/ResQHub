package com.resqhub.resqhub.controller;

import com.resqhub.resqhub.dto.IncidentRequest;
import com.resqhub.resqhub.dto.IncidentResponse;
import com.resqhub.resqhub.model.Incident;
import com.resqhub.resqhub.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @PostMapping
    public IncidentResponse createIncident(@Valid @RequestBody IncidentRequest request) {
        return incidentService.createIncident(request);
    }

    @GetMapping
    public List<Incident> getAllIncidents() {
        return incidentService.getAllIncidents();
    }
}
