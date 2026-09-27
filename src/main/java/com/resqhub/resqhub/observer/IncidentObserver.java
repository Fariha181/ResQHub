package com.resqhub.resqhub.observer;

public interface IncidentObserver {
    void onIncidentCreated(String title, String location, String agency);
}