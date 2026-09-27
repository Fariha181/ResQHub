package com.resqhub.resqhub.singleton;

import java.time.LocalDateTime;

public class SystemLogger {

    // 1. Private static instance 
    private static SystemLogger instance;

    // 2. Private constructor 
    private SystemLogger() {
        System.out.println("SystemLogger Initialized [Singleton Instance Created]");
    }

    // 3. Public static method 
    public static synchronized SystemLogger getInstance() {
        if (instance == null) {
            instance = new SystemLogger();
        }
        return instance;
    }

    // Logging Method
    public void log(String message) {
        System.out.println("[SYSTEM LOG " + LocalDateTime.now() + "] " + message);
    }
}