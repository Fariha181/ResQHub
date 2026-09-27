package com.resqhub.resqhub.factory;

import org.springframework.stereotype.Component;

@Component
public class ResponderFactory {

    public Responder getResponder(String incidentType) {
        if (incidentType == null) return null;

        if (incidentType.equalsIgnoreCase("FIRE")) {
            return new FireResponder();
        } else if (incidentType.equalsIgnoreCase("MEDICAL") || incidentType.equalsIgnoreCase("ACCIDENT")) {
            return new AmbulanceResponder();
        }
        return null;
    }
}
