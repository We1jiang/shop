package com.demo.shop.security;

public enum  ApplicationUserPermission {
    CLIENT_READ("client:read"),
    CLIENT_WRITE("client:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    public String getPermission() {
        return permission;
    }

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
