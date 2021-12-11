package com.example.demo.service;

import com.example.demo.enums.LoggingDestinations;
import com.example.demo.enums.LoggingLevels;
import com.example.demo.models.LogManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CustomLoggingLibrary implements LoggingLibrary {
    // TODO: Get it from configurations
    private static final String LOG_FILE_PATH = "/home/rohith/Documents/logfile.txt";
    private static final String REPLACE_VARIABLE = "%r";

    private LoggingLevels logLevel;
    private List<LoggingDestinations> loggingDestinationsList;
    private LogManagement infoLogManagement;
    private LogManagement debugLogManagement;
    private LogManagement warningLogManagement;
    private LogManagement errorLogManagement;

    public CustomLoggingLibrary(
            LogManagement infoLogManagement,
            LogManagement debugLogManagement,
            LogManagement warningLogManagement,
            LogManagement errorLogManagement,
            LoggingLevels logLevel,
            List<LoggingDestinations> loggingDestinationsList) {
        this.infoLogManagement = infoLogManagement;
        this.debugLogManagement = debugLogManagement;
        this.warningLogManagement = warningLogManagement;
        this.errorLogManagement = errorLogManagement;
        this.logLevel = logLevel;
        this.loggingDestinationsList = loggingDestinationsList;
    }

    @Override
    public void debug(String message) {
        if (logLevel.getValue() <= LoggingLevels.DEBUG.getValue()) {
            List<LoggingDestinations> debugDestinations = debugLogManagement.getLoggingDestinationsList();
            if (debugDestinations.contains(LoggingDestinations.CONSOLE)) {
                printToConsole(message, LoggingLevels.DEBUG);
            }
            if (debugDestinations.contains(LoggingDestinations.FILE)) {
                writeToFile(message, LoggingLevels.DEBUG);
            }
        }
    }

    @Override
    public void info(String message, Object variable) {
        if (message.contains(REPLACE_VARIABLE)) {
            message = message.replaceFirst(REPLACE_VARIABLE, variable.toString());
        }
        info(message);
    }

    @Override
    public void info(String message, Object... variables) {
        for (Object variable : variables) {
            if (message.contains(REPLACE_VARIABLE)) {
                message = message.replaceFirst(REPLACE_VARIABLE, variable.toString());
            }
        }
        info(message);
    }


    @Override
    public void info(String message) {
        if (logLevel.getValue() <= LoggingLevels.INFO.getValue()) {
            List<LoggingDestinations> infoDestinations = infoLogManagement.getLoggingDestinationsList();
            if (infoDestinations.contains(LoggingDestinations.CONSOLE)) {
                printToConsole(message, LoggingLevels.INFO);
            }
            if (infoDestinations.contains(LoggingDestinations.FILE)) {
                writeToFile(message, LoggingLevels.INFO);
            }
        }
    }

    @Override
    public void warning(String message) {
        if (logLevel.getValue() <= LoggingLevels.WARNING.getValue()) {
            List<LoggingDestinations> warningDestinations = warningLogManagement.getLoggingDestinationsList();
            if (warningDestinations.contains(LoggingDestinations.CONSOLE)) {
                printToConsole(message, LoggingLevels.WARNING);
            }
            if (warningDestinations.contains(LoggingDestinations.FILE)) {
                writeToFile(message, LoggingLevels.WARNING);
            }
        }
    }

    @Override
    public void error(String message) {
        if (logLevel.getValue() <= LoggingLevels.ERROR.getValue()) {
            List<LoggingDestinations> errorDestinations = errorLogManagement.getLoggingDestinationsList();
            if (errorDestinations.contains(LoggingDestinations.CONSOLE)) {
                printToConsole(message, LoggingLevels.ERROR);
            }
            if (errorDestinations.contains(LoggingDestinations.FILE)) {
                writeToFile(message, LoggingLevels.ERROR);
            }
        }
    }

    @Override
    public void setLogDestination(LoggingLevels levels, LogManagement logManagement) {
        if (levels == LoggingLevels.DEBUG) {
            this.debugLogManagement = logManagement;
        }
        if (levels == LoggingLevels.INFO) {
            this.infoLogManagement = logManagement;
        }
        if (levels == LoggingLevels.WARNING) {
            this.warningLogManagement = logManagement;
        }
        if (levels == LoggingLevels.ERROR) {
            this.errorLogManagement = logManagement;
        }
    }

    @Override
    public void setLogLevel(LoggingLevels level) {
        this.logLevel = level;
    }

    @Override
    public void attachPlatform(LoggingDestinations destination) {
        this.loggingDestinationsList.add(destination);
    }

    @Override
    public void detachPlatform(LoggingDestinations destination) {
        this.loggingDestinationsList.remove(destination);
    }

    private void printToConsole(String message, LoggingLevels level) {
        if (loggingDestinationsList.contains(LoggingDestinations.CONSOLE)) {
            String messageToPrint = getMessageToPrint(message, level);
            System.out.println(messageToPrint); //NOSONAR: Required to print to console
        }
    }


    private void writeToFile(String message, LoggingLevels level) {
        if (loggingDestinationsList.contains(LoggingDestinations.FILE)) {
            try {
                String messageToPrint = getMessageToPrint(message, level);
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true));
                bufferedWriter.append(messageToPrint);
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException exception) {
                System.out.println("Failed to log to file as file is non existent"); //NOSONAR
            }
        }
    }

    private String getMessageToPrint(String message, LoggingLevels level) {

        return String.format("%s [%s] %s ", level, LocalDateTime.now(), message);
    }
}
