package com.resqhub.resqhub.controller;

import com.resqhub.resqhub.dto.IncidentRequest;
import com.resqhub.resqhub.model.Incident;
import com.resqhub.resqhub.repository.IncidentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentRepository repository;

    public IncidentController(IncidentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Incident> getAllIncidents() {
        return repository.findAll();
    }

    @PostMapping
    public Incident createIncident(@RequestBody IncidentRequest request) {
        Incident incident = new Incident(
            request.getTitle(),
            request.getDescription(),
            request.getLocation(),
            request.getStatus()
        );
        return repository.save(incident);
    }
}
