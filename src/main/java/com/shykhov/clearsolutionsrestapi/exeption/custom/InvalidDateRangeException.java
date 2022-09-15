package com.shykhov.clearsolutionsrestapi.exeption.custom;

import java.time.LocalDate;

public class InvalidDateRangeException extends CustomExceptionDefault {
    public InvalidDateRangeException(LocalDate from, LocalDate to) {
        super("Invalid rage of dates, %s is more then %s".formatted(from.toString(), to.toString()));
    }
}
