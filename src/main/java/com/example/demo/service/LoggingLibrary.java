package com.example.demo.service;

import com.example.demo.enums.LoggingDestinations;
import com.example.demo.enums.LoggingLevels;
import com.example.demo.models.LogManagement;

public interface LoggingLibrary {
    void debug(String message);

    void info(String message);

    void info(String message, Object variable);

    void info(String message, Object... variables);

    void warning(String message);

    void error(String message);

    void setLogDestination(LoggingLevels levels, LogManagement logManagement);

    void setLogLevel(LoggingLevels level);

    void attachPlatform(LoggingDestinations destination);

    void detachPlatform(LoggingDestinations destination);
}
