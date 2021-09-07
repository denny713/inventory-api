package com.inventory.api.data.model.data;

import lombok.Data;

@Data
public class CheckResultData {

    public CheckResultData(Boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    private Boolean result;
    private String message;
}
