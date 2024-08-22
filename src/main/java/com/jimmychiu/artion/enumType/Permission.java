package com.jimmychiu.artion.enumType;

public enum Permission {
    ADMIN_LOGIN("AdminLogin","後台登入"),
    MEMBER_LOGIN("MemberLogin","會員登入");
    private final String code;
    private final String description;

    Permission(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return this.code;
    }
}
