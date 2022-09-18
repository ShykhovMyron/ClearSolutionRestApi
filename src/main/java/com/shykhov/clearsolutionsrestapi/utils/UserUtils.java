package com.shykhov.clearsolutionsrestapi.utils;

import com.shykhov.clearsolutionsrestapi.dao.entity.UserEntity;
import com.shykhov.clearsolutionsrestapi.model.request.UserDetailsRequestModel;
import com.shykhov.clearsolutionsrestapi.model.request.UserPatchRequestModel;
import com.shykhov.clearsolutionsrestapi.model.response.UserResponse;

import java.util.List;

import static java.util.Objects.nonNull;

public class UserUtils {

    private UserUtils() {
    }

    public static List<UserResponse> getResponse(List<UserEntity> saved) {
        return saved.stream().map(UserUtils::getResponse).toList();
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

    public static UserEntity getUserEntity(UserDetailsRequestModel userDetails) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress(userDetails.getAddress());
        userEntity.setEmail(userDetails.getEmail());
        userEntity.setBirthDate(userDetails.getBirthDate());
        userEntity.setFirstName(userDetails.getFirstName());
        userEntity.setLastName(userDetails.getLastName());
        userEntity.setPhoneNumber(userDetails.getPhoneNumber());

        return userEntity;
    }

    public static UserEntity patchUser(
            UserEntity targetUser,
            UserPatchRequestModel patch
    ) {
        if (nonNull(patch.getEmail())) targetUser.setEmail(patch.getEmail());
        if (nonNull(patch.getFirstName())) targetUser.setFirstName(patch.getFirstName());
        if (nonNull(patch.getLastName())) targetUser.setLastName(patch.getLastName());
        if (nonNull(patch.getBirthDate())) targetUser.setBirthDate(patch.getBirthDate());
        if (nonNull(patch.getAddress())) targetUser.setAddress(patch.getAddress());
        if (nonNull(patch.getPhoneNumber())) targetUser.setPhoneNumber(patch.getPhoneNumber());

        return targetUser;
    }
}
