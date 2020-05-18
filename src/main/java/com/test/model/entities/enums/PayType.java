package com.test.model.entities.enums;

import lombok.Getter;

public enum PayType {
    CREDIT_CARD("Credit Card"),
    PAYPAL("PayPal"),
    CASH("Cash"),
    BITCOIN("Bitcoin");

    private @Getter String name;

    PayType(String name) {
        this.name = name;
    }
}
