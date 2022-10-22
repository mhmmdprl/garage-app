package com.garage.util;

import com.garage.enums.ResponseType;
import com.garage.response.ResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<Object> createErrorMessage(String message, HttpStatus httpStatus, Object data) {
        return new ResponseEntity<>(new ResponseMessage(false, ResponseType.ERROR, message, data), new HttpHeaders(), httpStatus);
    }

    public static  ResponseMessage createResponseMessage(Boolean success, ResponseType type, Object message, Object result) {
        ResponseMessage responseMessage;

        if (result != null) {
            responseMessage = new ResponseMessage(success, type, message, result);
        } else {
            responseMessage = new ResponseMessage(success, type, message);
        }
        return responseMessage;
    }
    public static <T> ResponseMessage createSuccessMessage(T result) {
        return createResponseMessage(true, ResponseType.SUCCESS, null, result);
    }
}
