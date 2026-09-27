package com.resqhub.resqhub.factory;

public class FireResponder implements Responder {
    @Override
    public String getAgencyType() { 
        return "FIRE"; 
    }

    @Override
    public String dispatch(String location) {
        return "Firetruck unit dispatched to: " + location;
    }
}