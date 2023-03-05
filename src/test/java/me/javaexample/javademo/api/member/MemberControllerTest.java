package me.javaexample.javademo.api.member;

import me.javaexample.javademo.api.IntegrationTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
class MemberControllerTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void _01_멤버조회() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/member"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name", is("kang")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name", is("kim")));
    }

    @Test
    void _02_멤버추가() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"alice\", \"email\":\"alice@gmail.com\", \"category\":\"M001\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/member"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name", is("kang")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name", is("kim")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[2].name", is("alice")));
    }
}