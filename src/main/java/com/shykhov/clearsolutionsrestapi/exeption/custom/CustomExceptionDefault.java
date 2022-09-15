package com.shykhov.clearsolutionsrestapi.exeption.custom;

public abstract class CustomExceptionDefault extends Exception {
    public CustomExceptionDefault(String message) {
        super(message);
    }
}
