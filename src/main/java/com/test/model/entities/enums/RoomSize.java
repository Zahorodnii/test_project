package com.test.model.entities.enums;

import lombok.Getter;

public enum RoomSize {
    SINGLE_ROOM("Single Room"),
    DOUBLE_ROOM("Double Room"),
    BUSINESS_SUITE("Business Suite"),
    PRESIDENTIAL_SUITE("Presidential Suite"),
    STOREROOM("Storeroom");

    private @Getter String roomName;

    RoomSize(String roomName) {
        this.roomName = roomName;
    }
}
