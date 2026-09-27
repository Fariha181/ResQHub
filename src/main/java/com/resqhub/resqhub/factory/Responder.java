package com.resqhub.resqhub.factory;

public interface Responder {
    String getAgencyType();
    String dispatch(String location);
}
