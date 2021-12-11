package com.example.demo.enums;

public enum LoggingLevels {
    DEBUG(0), INFO(1), WARNING(2), ERROR(3);
    private final int priority;

    LoggingLevels(int priority) {
        this.priority = priority;
    }

    public int getValue() {
        return this.priority;
    }

}
