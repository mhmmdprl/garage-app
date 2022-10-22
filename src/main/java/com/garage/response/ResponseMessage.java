package com.garage.response;

import com.garage.enums.ResponseType;
import java.io.Serializable;

public class ResponseMessage<T> implements Serializable {

    private Boolean success;
    private ResponseType type;
    private Object message;
    private T result;

    public ResponseMessage() {

    }

    public ResponseMessage(Boolean success, ResponseType type, Object meessage) {
        this.success = success;
        this.type = type;
        this.message = meessage;
    }

    public ResponseMessage(Boolean success, ResponseType type, Object message, T result) {
        this.success = success;
        this.type = type;
        this.message = message;
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
