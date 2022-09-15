package com.shykhov.clearsolutionsrestapi.exeption;

import com.shykhov.clearsolutionsrestapi.exeption.custom.CustomExceptionDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Controller
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

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
}
