package com.example.demo;

import com.example.demo.enums.LoggingDestinations;
import com.example.demo.enums.LoggingLevels;
import com.example.demo.models.LogManagement;
import com.example.demo.service.CustomLoggingLibrary;
import com.example.demo.service.LoggingLibrary;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LoggingTests {

    private final List<LoggingDestinations> loggingDestinationsList = new ArrayList<>() {{
        add(LoggingDestinations.FILE);
        add(LoggingDestinations.CONSOLE);
    }};
    private final List<LoggingDestinations> consoleDestinationList = new ArrayList<>() {{
        add(LoggingDestinations.CONSOLE);
    }};
    private final List<LoggingDestinations> fileDestinationList = new ArrayList<>() {{
        add(LoggingDestinations.FILE);
    }};

    LoggingLibrary logger = new CustomLoggingLibrary(
            getDefaultLogManagement(LoggingLevels.INFO),
            getDefaultLogManagement(LoggingLevels.DEBUG),
            getDefaultLogManagement(LoggingLevels.WARNING),
            getDefaultLogManagement(LoggingLevels.ERROR),
            LoggingLevels.INFO,
            new ArrayList<>(loggingDestinationsList)
    );

    private LogManagement getDefaultLogManagement(LoggingLevels levels) {
        return new LogManagement(levels, loggingDestinationsList);
    }

    @Test
    void testLoggers() {
        // TODO: Write tests to automatically read the file and verify the content instead of manually.
        logger.info("information printed by custom loggers to both file and console");
        logger.setLogDestination(LoggingLevels.INFO, new LogManagement(LoggingLevels.INFO, consoleDestinationList));
        logger.info("Information printed by custom logger only to console");
        logger.setLogDestination(LoggingLevels.INFO, new LogManagement(LoggingLevels.INFO, fileDestinationList));
        logger.info("Information printed by custom logger only to the file");

        logger.error("Error is being printed to console and file");
        logger.detachPlatform(LoggingDestinations.CONSOLE);
        logger.info("Error will now only be printed to file");
        logger.attachPlatform(LoggingDestinations.CONSOLE);
        logger.info("Error will be printed to console again");

        logger.setLogLevel(LoggingLevels.ERROR);
        logger.warning("Warning is not printed to console");
        logger.error("Error is printed after setting log level");

        logger.setLogDestination(LoggingLevels.INFO, new LogManagement(LoggingLevels.INFO, loggingDestinationsList));
        logger.setLogLevel(LoggingLevels.DEBUG);
        logger.info("Printing message with parameter %r", 4);

        logger.info("Printing message with multiple parameters %r %r", 4, 9);

        logger.info("Printing message with %r parameters which are %r %r and %r", 4, 1, 2, 3);
    }
}
