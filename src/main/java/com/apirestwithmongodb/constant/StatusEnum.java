package com.apirestwithmongodb.constant;

public enum StatusEnum {

    INACTIVE("INACTIVE"),
    ACTIVE("ACTIVE"),
    PENDING("PENDING");

    private String status;

    private StatusEnum(String status) { this.status = status; }

    public String getStatus() { return status; }

}


