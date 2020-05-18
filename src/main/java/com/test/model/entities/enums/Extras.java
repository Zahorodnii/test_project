package com.test.model.entities.enums;

import lombok.Getter;

public enum Extras {
    BREAKFAST("Breakfast"),
    TV("TV"),
    WIFI("WiFi"),
    PARKING_LOT("Parking Lot"),
    BALCONY("Balcony");

    private @Getter String name;
    private @Getter boolean checked;

    Extras(String name) {
        this.name = name;
    }

    public Extras check() {
        if (!checked) {
            checked = true;
        }

        return this;
    }

    public Extras uncheck() {
        if (checked) {
            checked = false;
        }

        return this;
    }

    public static void uncheckAll() {
        for (Extras extra : values()) {
            extra.uncheck();
        }
    }
}
