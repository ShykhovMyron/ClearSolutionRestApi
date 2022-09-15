package com.shykhov.clearsolutionsrestapi.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shykhov.clearsolutionsrestapi.validation.ValidAge;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
public class UserDetailsRequestModel {

    @Email(message = "Email is incorrect")
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotNull(message = "Last name cannot be empty")
    private String lastName;

    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date cannot be greater than today's date")
    @ValidAge
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate birthDate;

    private String phoneNumber;

    private String address;
}
