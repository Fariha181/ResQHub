package com.resqhub.resqhub.strategy;

import org.springframework.stereotype.Component;

@Component("fastestRoute")
public class FastestRouteStrategy implements RouteStrategy {
    @Override
    public String calculateRoute(String startLocation, String endLocation) {
        return "Fastest Route via Highway from " + startLocation + " to " + endLocation + " (ETA: 12 mins)";
    }
}