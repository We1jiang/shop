package com.demo.shop.security;

public enum  ApplicationUserPermission {
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    public String getPermission() {
        return permission;
    }

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
