package com.shykhov.clearsolutionsrestapi.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shykhov.clearsolutionsrestapi.controller.UserController;
import com.shykhov.clearsolutionsrestapi.dao.entity.UserEntity;
import com.shykhov.clearsolutionsrestapi.model.request.UserPatchRequestModel;
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
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class EditUsersUnitTest {

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
    void editUsersIncorrectEmailTest() throws Exception {
        // Arrange
        String email = "testgmail.com";
        UserPatchRequestModel user = new UserPatchRequestModel();
        user.setEmail(email);
        // Action & Assert
        mvc.perform(patch("/users/{id}", 1)
                        .content(mapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[*].rejectedValue").value(email));
    }

    @Test
    void editUsersTooLowAgeTest() throws Exception {
        // Arrange
        LocalDate lowDateBirth = LocalDate.now().minus(age, YEARS).plus(1, DAYS);
        UserPatchRequestModel user = new UserPatchRequestModel();
        user.setBirthDate(lowDateBirth);
        // Action & Assert
        mvc.perform(patch("/users/{id}", 1)
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
    void editUsersFirstNameEmptyTest(String firstName) throws Exception {
        // Arrange
        long id = 1;
        UserEntity userEntity = getValidUserEntity(age);
        userEntity.setId(id);
        when(userService.findUser(id)).thenReturn(userEntity);

        UserPatchRequestModel user = new UserPatchRequestModel();
        user.setFirstName(firstName);
        // Action & Assert
        mvc.perform(patch("/users/{id}", 1)
                        .content(mapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(mapper.writeValueAsString(getResponse(userEntity))));
    }

    @ParameterizedTest
    @CsvSource(value =
            {"null", "''"},
            nullValues = {"null"})
    void editUsersLastNameEmptyTest(String lastName) throws Exception {
        // Arrange
        long id = 1;
        UserEntity userEntity = getValidUserEntity(age);
        userEntity.setId(id);
        when(userService.findUser(id)).thenReturn(userEntity);

        UserPatchRequestModel user = new UserPatchRequestModel();
        user.setFirstName(lastName);
        // Action & Assert
        mvc.perform(patch("/users/{id}", 1)
                        .content(mapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(mapper.writeValueAsString(getResponse(userEntity))));
    }

    @Test
    void editUsersAllParamsEmptyTest() throws Exception {
        // Arrange
        long id = 1;
        UserEntity userEntity = getValidUserEntity(age);
        userEntity.setId(id);
        when(userService.findUser(id)).thenReturn(userEntity);

        UserPatchRequestModel user = new UserPatchRequestModel("", "", "", null, "", "");
        // Action & Assert
        mvc.perform(patch("/users/{id}", 1)
                        .content(mapper.writeValueAsString(user))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(mapper.writeValueAsString(getResponse(userEntity))));
    }
}
