package com.FT_locations.Models;

public enum Status {
    Decommissioned,
    ACTIVE,
    INACTIVE,
    REPAIRING;

    public static String[] names() {
        return new String[] { "ACTIVE","INACTIVE", "DECOMMISSIONED", "REPAIRING" };
    }
}