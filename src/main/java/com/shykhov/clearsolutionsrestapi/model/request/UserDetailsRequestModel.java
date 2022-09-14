package com.shykhov.clearsolutionsrestapi.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
public class UserDetailsRequestModel {

    @Email(message = "Email is incorrect\n")
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @Past(message = "Birth date cannot be ")
    @NotNull(message = "Birth date cannot be null")
    private LocalDate birthDate;

    private String phoneNumber;

    private String address;
}
