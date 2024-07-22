package com.ecommerce.project.ecommerce.enums;

public enum SaleStatus {
    WAITING_PAYMENT(1),
    CONFIRMED(2),
    CANCELED(3);

    private int code;

    private SaleStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SaleStatus valueOf(int code) {
        for (SaleStatus value : SaleStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code");
    }
}
