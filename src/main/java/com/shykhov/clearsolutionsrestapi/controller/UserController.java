package com.shykhov.clearsolutionsrestapi.controller;

import com.shykhov.clearsolutionsrestapi.dao.entity.UserEntity;
import com.shykhov.clearsolutionsrestapi.exeption.custom.InvalidDateRangeException;
import com.shykhov.clearsolutionsrestapi.exeption.custom.UserDoesNotExistException;
import com.shykhov.clearsolutionsrestapi.model.request.UserDetailsRequestModel;
import com.shykhov.clearsolutionsrestapi.model.response.UserResponse;
import com.shykhov.clearsolutionsrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static com.shykhov.clearsolutionsrestapi.utils.UserUtils.getResponse;
import static com.shykhov.clearsolutionsrestapi.utils.ValidationUtils.isDateIntervalValid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("users")
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserDetailsRequestModel userDetails
    ) {
        UserEntity saved = userService.createUser(userDetails);

        return new ResponseEntity<>(getResponse(saved), HttpStatus.OK);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<UserResponse> replaceUser(
            @PathVariable long id,
            @Valid @RequestBody UserDetailsRequestModel userDetails
    ) throws UserDoesNotExistException {
        UserEntity saved = userService.replaceUser(id, userDetails);

        return new ResponseEntity<>(getResponse(saved), HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable long id) throws UserDoesNotExistException {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("users")
    public ResponseEntity<List<UserResponse>> getUsers(
            @RequestParam(value = "from") LocalDate fromDate,
            @RequestParam(value = "to") LocalDate toDate
    ) throws InvalidDateRangeException {
        if (!isDateIntervalValid(fromDate, toDate)) throw new InvalidDateRangeException(fromDate, toDate);

        List<UserEntity> usersTimeInterval = userService.getUsersTimeInterval(fromDate, toDate);

        return new ResponseEntity<>(getResponse(usersTimeInterval), HttpStatus.OK);
    }
}
