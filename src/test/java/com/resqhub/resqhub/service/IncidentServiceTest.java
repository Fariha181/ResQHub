package com.resqhub.resqhub.service;

import com.resqhub.resqhub.dto.IncidentRequest;
import com.resqhub.resqhub.dto.IncidentResponse;
import com.resqhub.resqhub.factory.ResponderFactory;
import com.resqhub.resqhub.model.Incident;
import com.resqhub.resqhub.observer.AgencyNotifier;
import com.resqhub.resqhub.repository.IncidentRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class IncidentServiceTest {

    private final IncidentRepository incidentRepository = mock(IncidentRepository.class);
    private final ResponderFactory responderFactory = new ResponderFactory();
    private final AgencyNotifier agencyNotifier = mock(AgencyNotifier.class);
    private final IncidentService incidentService = new IncidentService(
            incidentRepository, responderFactory, agencyNotifier
    );

    @Test
    void createsIncidentAssignsMedicalAgencyAndPersistsIt() {
        IncidentRequest request = request("MEDICAL");
        when(incidentRepository.save(any(Incident.class))).thenAnswer(invocation -> {
            Incident incident = invocation.getArgument(0);
            incident.setId(42L);
            return incident;
        });

        IncidentResponse response = incidentService.createIncident(request);

        assertEquals(42L, response.getId());
        assertEquals("MEDICAL", response.getIncidentType());
        assertEquals("MEDICAL", response.getAssignedAgency());
        assertEquals("OPEN", response.getStatus());
        verify(incidentRepository).save(any(Incident.class));
        verify(agencyNotifier).onIncidentCreated("Medical incident", "Dhaka", "MEDICAL");
    }

    @Test
    void assignsGeneralAgencyForUnknownIncidentType() {
        IncidentRequest request = request("NATURAL_DISASTER");
        when(incidentRepository.save(any(Incident.class))).thenAnswer(invocation -> invocation.getArgument(0));

        IncidentResponse response = incidentService.createIncident(request);

        assertEquals("GENERAL", response.getAssignedAgency());
    }

    @Test
    void assignsFireAgencyForFireIncident() {
        IncidentRequest request = request("FIRE");
        when(incidentRepository.save(any(Incident.class))).thenAnswer(invocation -> invocation.getArgument(0));

        IncidentResponse response = incidentService.createIncident(request);

        assertEquals("FIRE", response.getAssignedAgency());
    }

    @Test
    void assignsMedicalAgencyForAccidentIncident() {
        IncidentRequest request = request("ACCIDENT");
        when(incidentRepository.save(any(Incident.class))).thenAnswer(invocation -> invocation.getArgument(0));

        IncidentResponse response = incidentService.createIncident(request);

        assertEquals("MEDICAL", response.getAssignedAgency());
    }

    private IncidentRequest request(String incidentType) {
        IncidentRequest request = new IncidentRequest();
        request.setTitle("Medical incident");
        request.setDescription("Multiple people need assistance");
        request.setLocation("Dhaka");
        request.setIncidentType(incidentType);
        return request;
    }
}
