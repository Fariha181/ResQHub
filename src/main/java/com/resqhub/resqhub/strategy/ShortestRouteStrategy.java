package com.resqhub.resqhub.strategy;

import org.springframework.stereotype.Component;

@Component("shortestRoute")
public class ShortestRouteStrategy implements RouteStrategy {
    @Override
    public String calculateRoute(String startLocation, String endLocation) {
        return "Shortest Route via Local Roads from " + startLocation + " to " + endLocation + " (Distance: 4.2 km)";
    }
}
