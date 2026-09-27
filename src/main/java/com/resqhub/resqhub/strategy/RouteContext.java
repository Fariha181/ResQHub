package com.resqhub.resqhub.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RouteContext {

    // Spring Boot অটোমেটিক সব RouteStrategy Bean এই Map-এ ইনজেক্ট করবে
    @Autowired
    private Map<String, RouteStrategy> routeStrategies;

    public String executeStrategy(String strategyType, String start, String end) {
        RouteStrategy strategy = routeStrategies.get(strategyType);
        if (strategy == null) {
            return "Default Route selected for " + start + " to " + end;
        }
        return strategy.calculateRoute(start, end);
    }
}
