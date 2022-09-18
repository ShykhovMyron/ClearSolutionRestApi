package com.shykhov.clearsolutionsrestapi.exeption;

import com.shykhov.clearsolutionsrestapi.exeption.custom.CustomExceptionDefault;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@RequiredArgsConstructor
public class GeneralExceptionHandler {

    private final BasicErrorController errorController;

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(ex, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(value = {CustomExceptionDefault.class})
    public ResponseEntity<Object> handleAnyException(CustomExceptionDefault ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<>(ex.getLocalizedMessage(), new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<Map<String, Object>> defaultErrorHandler(Exception ex, HttpServletRequest request) {
        request.setAttribute("javax.servlet.error.request_uri", request.getPathInfo());
        request.setAttribute("javax.servlet.error.status_code", 400);
        return errorController.error(request);
    }
}
