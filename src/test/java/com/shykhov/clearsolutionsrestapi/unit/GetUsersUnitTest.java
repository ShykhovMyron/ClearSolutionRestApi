package com.shykhov.clearsolutionsrestapi.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shykhov.clearsolutionsrestapi.controller.UserController;
import com.shykhov.clearsolutionsrestapi.dao.entity.UserEntity;
import com.shykhov.clearsolutionsrestapi.exeption.custom.InvalidDateRangeException;
import com.shykhov.clearsolutionsrestapi.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static com.shykhov.clearsolutionsrestapi.utils.UserTestUtils.getValidUserEntity;
import static com.shykhov.clearsolutionsrestapi.utils.UserUtils.getResponse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class GetUsersUnitTest {

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
    void getUsersValidTest() throws Exception {
        // Arrange
        List<UserEntity> usersTimeInterval = List.of(getValidUserEntity(age));
        LocalDate fromDate = LocalDate.of(2000, 12, 12);
        LocalDate toDate = LocalDate.of(2000, 12, 13);

        when(userService.getUsersTimeInterval(fromDate, toDate)).thenReturn(usersTimeInterval);
        // Action & Assert
        mvc.perform(get("/users")
                        .param("from", fromDate.toString())
                        .param("to", toDate.toString()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(mapper.writeValueAsString(getResponse(usersTimeInterval))));
    }

    @Test
    void getUsersInvalidRageOfDatesTest() throws Exception {
        // Arrange
        LocalDate fromDate = LocalDate.of(2000, 12, 13);
        LocalDate toDate = LocalDate.of(2000, 12, 12);
        // Action & Assert
        mvc.perform(get("/users")
                        .param("from", fromDate.toString())
                        .param("to", toDate.toString()))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidDateRangeException));
    }
}
