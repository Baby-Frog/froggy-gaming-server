package com.forggygaming.froggygamingserver.entity;

import lombok.Data;

@Data
public class ResponseObject {
    private String status;
    private String description;
    private Object data;

    public ResponseObject() {
    }

    public ResponseObject(String status, String description, Object data) {
        this.status = status;
        this.description = description;
        this.data = data;
    }
}
