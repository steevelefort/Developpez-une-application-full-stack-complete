package com.openclassrooms.mddapi.dto.response;

import lombok.Data;

/**
 * Basic response with a message
 */
@Data
public class BasicResponse {
    private String message;

    public BasicResponse(String message) {
        this.message = message;
    }
}
