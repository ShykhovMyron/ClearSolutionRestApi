package com.shykhov.clearsolutionsrestapi.exeption.custom;

public class UserDoesNotExistException extends CustomExceptionDefault {
    public UserDoesNotExistException(long id) {
        super("User with id '%s' doesn't exist".formatted(id));
    }
}
