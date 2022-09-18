package com.shykhov.clearsolutionsrestapi.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shykhov.clearsolutionsrestapi.controller.UserController;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class DeleteUsersUnitTest {

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
    void deleteUsersValidTest() throws Exception {
        this.mvc.perform(delete("/users/{id}", 1))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
