package com.shykhov.clearsolutionsrestapi.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shykhov.clearsolutionsrestapi.controller.UserController;
import com.shykhov.clearsolutionsrestapi.dao.entity.UserEntity;
import com.shykhov.clearsolutionsrestapi.model.request.UserDetailsRequestModel;
import com.shykhov.clearsolutionsrestapi.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.shykhov.clearsolutionsrestapi.utils.UserTestUtils.getValidUserEntity;
import static com.shykhov.clearsolutionsrestapi.utils.UserUtils.getResponse;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.YEARS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class ReplaceUsersUnitTest {

    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    @Value("${user.age}")
    private int age;

    @BeforeAll
    static void beforeAll() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    void replaceUsersValidTest() throws Exception {
        // Arrange
        long id = 1;
        UserEntity user = getValidUserEntity(age);
        user.setId(id);
        doReturn(user).when(userService).replaceUser(eq(id), any(UserDetailsRequestModel.class));
        // Action & Assert
        mvc.perform(put("/users/{id}", id)
                        .content(mapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(mapper.writeValueAsString(getResponse(user))));
    }

    @Test
    void replaceUsersIncorrectEmailTest() throws Exception {
        // Arrange
        UserEntity user = getValidUserEntity(age);

        String incorrectEmail = "testgmail.com";
        user.setEmail(incorrectEmail);
        // Action & Assert
        mvc.perform(put("/users/{id}", 1)
                        .content(mapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[*].rejectedValue").value(incorrectEmail));
    }

    @Test
    void replaceUsersTooLowAgeTest() throws Exception {
        // Arrange
        UserEntity user = getValidUserEntity(age);

        LocalDate lowDateBirth = LocalDate.now().minus(age, YEARS).plus(1, DAYS);
        user.setBirthDate(lowDateBirth);
        // Action & Assert
        mvc.perform(put("/users/{id}", 1)
                        .content(mapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[*].rejectedValue").value(lowDateBirth.toString()));
    }

    @ParameterizedTest
    @CsvSource(value =
            {"null", "''"},
            nullValues = {"null"})
    void replaceUsersFirstNameEmptyTest(String firstName) throws Exception {
        // Arrange
        UserEntity user = getValidUserEntity(age);
        user.setFirstName(firstName);
        // Action & Assert
        mvc.perform(put("/users/{id}", 1)
                        .content(mapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[*].rejectedValue").exists());
    }

    @ParameterizedTest
    @CsvSource(value =
            {"null", "''"},
            nullValues = {"null"})
    void replaceUsersLastNameEmptyTest(String lastName) throws Exception {
        // Arrange
        UserEntity user = getValidUserEntity(age);
        user.setFirstName(lastName);
        // Action & Assert
        mvc.perform(put("/users/{id}", 1)
                        .content(mapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[*].rejectedValue").exists());
    }

    @ParameterizedTest
    @CsvSource(value =
            {"null", "''"},
            nullValues = {"null"})
    void replaceUsersAddressEmptyTest(String address) throws Exception {
        // Arrange
        long id = 1;
        UserEntity user = getValidUserEntity(age);

        user.setId(id);
        user.setAddress(address);
        doReturn(user).when(userService).replaceUser(eq(id), any(UserDetailsRequestModel.class));        // Action & Assert
        mvc.perform(put("/users/{id}", id)
                        .content(mapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(mapper.writeValueAsString(getResponse(user))));
    }

    @ParameterizedTest
    @CsvSource(value =
            {"null", "''"},
            nullValues = {"null"})
    void replaceUsersPhoneNumberEmptyTest(String phoneNumber) throws Exception {
        // Arrange
        long id = 1;
        UserEntity user = getValidUserEntity(age);

        user.setId(id);
        user.setPhoneNumber(phoneNumber);
        doReturn(user).when(userService).replaceUser(eq(id), any(UserDetailsRequestModel.class));        // Action & Assert
        // Action & Assert
        mvc.perform(put("/users/{id}", id)
                        .content(mapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(mapper.writeValueAsString(getResponse(user))));
    }
}
