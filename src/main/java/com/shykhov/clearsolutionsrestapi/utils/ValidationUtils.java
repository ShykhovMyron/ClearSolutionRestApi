package com.shykhov.clearsolutionsrestapi.utils;

import java.time.LocalDate;

public class ValidationUtils {
    private ValidationUtils() {
    }

    public static boolean isDateIntervalValid(LocalDate fromDate, LocalDate toDate) {
        return fromDate.isBefore(toDate);
    }
}
