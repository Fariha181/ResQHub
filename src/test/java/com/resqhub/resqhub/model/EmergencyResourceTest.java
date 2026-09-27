package com.resqhub.resqhub.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmergencyResourceTest {

    private EmergencyResource resource;

    @BeforeEach
    void setUp() {
        resource = new EmergencyResource();
    }

    @Test
    void testSuccessfulAmbulanceReservation() {
        boolean reserved = resource.reserveAmbulance("Hospital-A");
        assertTrue(reserved);
        assertEquals(4, resource.getAvailableAmbulances());
    }

    @Test
    void testExhaustionOfAmbulances() {
        for (int i = 0; i < 5; i++) {
            assertTrue(resource.reserveAmbulance("User-" + i));
        }

        boolean extraReservation = resource.reserveAmbulance("Extra-User");
        assertFalse(extraReservation);
        assertEquals(0, resource.getAvailableAmbulances());
    }
}