package com.shykhov.clearsolutionsrestapi.model.request;

import com.shykhov.clearsolutionsrestapi.validation.ValidAge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter

@AllArgsConstructor
public class UserPatchRequestModel {
    @Email(message = "Email is incorrect")
    private String email;
    private String firstName;
    private String lastName;
    @Past(message = "Birth date cannot be greater than today's date")
    @ValidAge
    private LocalDate birthDate;
    private String phoneNumber;
    private String address;

    public UserPatchRequestModel() {
    }
}
