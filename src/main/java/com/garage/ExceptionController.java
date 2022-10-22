package com.garage;

import com.garage.exception.CustomException;
import com.garage.util.ResponseUtil;
import com.garage.util.StringUtil;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ResponseBody
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        LOGGER.error(e.getMessage());
        return ResponseUtil.createErrorMessage(StringUtil.messageFormat(e.getMessage(), e.getParameters()),
            HttpStatus.valueOf(e.getHttpStatus().value()), null);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        // Get all errors
        List<String> validationErrors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getField() + " -> " + x.getDefaultMessage())
            .collect(Collectors.toList());

        body.put("errors", validationErrors);

        return ResponseUtil.createErrorMessage("validation errors",
            HttpStatus.valueOf(status.value()), body);

    }

}
