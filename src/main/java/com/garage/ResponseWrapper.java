package com.garage;

import com.garage.enums.ResponseType;
import com.garage.response.ResponseMessage;
import com.garage.util.ResponseUtil;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseWrapper implements ResponseBodyAdvice<Object> {

    private static Set<Class<?>> RAW_TYPES = new HashSet<>(Arrays.asList(
        ResponseEntity.class,
        ResponseMessage.class
    ));

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (checkBody(body)) {
            return ResponseUtil.createResponseMessage(true, ResponseType.SUCCESS, null, body);
        }
        return body;
    }

    private boolean checkBody(Object body) {
        if (body == null) {
            return true;
        }

        Class<?> type = body.getClass();
        if (RAW_TYPES.contains(type)) {
            return false;
        }
        return true;
    }
}
