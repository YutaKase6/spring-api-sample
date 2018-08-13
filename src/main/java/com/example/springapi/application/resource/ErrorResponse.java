package com.example.springapi.application.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/**
 * エラーレスポンスのマッピング用クラス(Json)
 */
@Value
public class ErrorResponse {
    @JsonProperty("Error")
    private Error error;

    public ErrorResponse(String message, String detail, String code) {
        this.error = new Error(message, detail, code);
    }

    @Value
    private class Error {
        @JsonProperty("Message")
        private final String message;
        @JsonProperty("Detail")
        private final String detail;
        @JsonProperty("Code")
        private final String code;
    }
}
