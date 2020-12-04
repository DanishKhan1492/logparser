package com.elixirtask.parselogs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Muhammad Danish Khan
 * @created 4/12/20 - 2:19 AM
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage<T> {
    private final String message;
    private final Integer status;
    private T data;

    public ResponseMessage(String message, Integer status) {
        this.message = message;
        this.status = status;
    }

    public ResponseMessage(String message, Integer status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
