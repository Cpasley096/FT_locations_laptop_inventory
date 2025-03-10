package com.FT_locations.Models;


public record Args(String orderBy, String orderMode, Status status, String format) {
    public Args {
        if (orderBy == null || orderBy.isEmpty()) {
            orderBy = "id";
        }
        if (orderMode == null || orderMode.isEmpty()) {
            orderMode = "ASC";
        }
    }
}
