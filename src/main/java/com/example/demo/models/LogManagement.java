package com.example.demo.models;

import com.example.demo.enums.LoggingDestinations;
import com.example.demo.enums.LoggingLevels;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LogManagement {
    private LoggingLevels loggingLevel;
    private List<LoggingDestinations> loggingDestinationsList;
}
