package com.ecommerce.project.ecommerce.enums;

public enum SaleStatus {
    SHIPPED (1),
    CONFIRMED (2),
    CANCELED (3);

    private int code;

    private SaleStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SaleStatus valueOf(int code) {
        for (SaleStatus status : SaleStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código Inválido");
    }
}
