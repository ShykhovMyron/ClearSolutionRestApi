package com.shykhov.clearsolutionsrestapi.exeption.custom;

public class UserNotFoundException extends CustomExceptionDefault {
    public UserNotFoundException(long id) {
        super("User with id '%s' doesn't exist".formatted(id));
    }
}
