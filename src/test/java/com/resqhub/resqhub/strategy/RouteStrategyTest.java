package com.resqhub.resqhub.strategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RouteStrategyTest {

    @Test
    void testFastestRouteStrategy() {
        RouteStrategy strategy = new FastestRouteStrategy();
        String result = strategy.calculateRoute("Station-A", "Dhanmondi");

        assertNotNull(result);
        assertTrue(result.contains("Fastest Route via Highway"));
    }

    @Test
    void testShortestRouteStrategy() {
        RouteStrategy strategy = new ShortestRouteStrategy();
        String result = strategy.calculateRoute("Station-A", "Dhanmondi");

        assertNotNull(result);
        assertTrue(result.contains("Shortest Route via Local Roads"));
    }
}