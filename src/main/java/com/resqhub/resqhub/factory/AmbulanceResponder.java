package com.resqhub.resqhub.factory;

public class AmbulanceResponder implements Responder {
    @Override
    public String getAgencyType() { 
        return "MEDICAL"; 
    }

    @Override
    public String dispatch(String location) {
        return "Ambulance unit dispatched to: " + location;
    }
}
