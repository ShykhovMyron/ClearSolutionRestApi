package com.shykhov.clearsolutionsrestapi.utils;

import com.shykhov.clearsolutionsrestapi.dao.entity.UserEntity;
import com.shykhov.clearsolutionsrestapi.model.response.UserResponse;

public class UserUtils {

    private UserUtils() {
    }

    public static UserResponse getResponse(UserEntity saved) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(saved.getId());
        userResponse.setAddress(saved.getAddress());
        userResponse.setBirthDate(saved.getBirthDate());
        userResponse.setEmail(saved.getEmail());
        userResponse.setFirstName(saved.getFirstName());
        userResponse.setLastName(saved.getLastName());
        userResponse.setPhoneNumber(saved.getPhoneNumber());

        return userResponse;
    }
}
