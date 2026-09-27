package com.resqhub.resqhub.controller;

import com.resqhub.resqhub.singleton.SystemLogger;
import com.resqhub.resqhub.strategy.RouteContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteContext routeContext;

    @GetMapping("/dispatch")
    public String dispatchResponder(
            @RequestParam String strategy,
            @RequestParam String start,
            @RequestParam String destination) {

        SystemLogger.getInstance().log("Calculating dispatch route using strategy: " + strategy);

        return routeContext.executeStrategy(strategy, start, destination);
    }
}
