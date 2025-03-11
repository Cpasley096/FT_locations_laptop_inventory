package com.FT_locations.Models;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public enum Status {
    DECOMMISSIONED,
    ASSIGNED,
    AVAILABLE,
    REPAIRING;

    public static String[] names() {
        return new String[] { "ASSIGNED","AVAILABLE", "DECOMMISSIONED", "REPAIRING" };
    }
    public static String getAllowedStatuses() {
        return String.join(", ", List.of(Status.values()).toString());
    }

    public static Status fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return Status.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid status value: " + value + ". Allowed values: " + getAllowedStatuses()
            );
        }
    }
}