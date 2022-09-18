package com.shykhov.clearsolutionsrestapi.utils;

import com.shykhov.clearsolutionsrestapi.dao.entity.UserEntity;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

public class UserTestUtils {
    public static UserEntity getValidUserEntity(int age) {
        String email = "test@gmail.com";
        String firstName = "first";
        String lastName = "second";
        LocalDate birthDate = LocalDate.now().minus(age, YEARS);
        String phoneNumber = "+240000000000";
        String address = "Random Street";

        return new UserEntity(email, firstName, lastName, birthDate, phoneNumber, address);
    }
}
