package com.resqhub.resqhub.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncidentRequestValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void rejectsRequestWithMissingRequiredFields() {
        IncidentRequest request = new IncidentRequest();

        var violations = validator.validate(request);

        assertEquals(4, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("title")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("description")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("location")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("incidentType")));
    }
}
