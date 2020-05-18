package com.test.model.entities;

import com.test.model.entities.enums.PayType;
import com.test.model.entities.enums.RoomSize;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
public class TripInfo {
    private String arrivalDate;
    private String departureDate;
    private RoomSize roomSize;
    private int roomQuantity;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String streetName;
    private String streetNumber;
    private String zipCode;
    private String state;
    private String city;
    private PayType payType;
    private String note;
    private boolean reminder;
    private boolean newsletter = true;
    private boolean informationConfirm;

    @Singular
    private List<String> tags;
}
