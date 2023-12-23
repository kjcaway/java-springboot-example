package me.javaexample.javademo.api.member;

import me.javaexample.javademo.api.EnableMockMvc;
import me.javaexample.javademo.api.IntegrationTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@Tag("api")
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@EnableMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
class MemberControllerTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void _01_멤버조회() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/member"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(3)))
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
            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(4)));
    }

    @Test
    void _03_멤버하나조회() throws Exception {
        long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/member/search/" + id))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("kang")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.email", is("kang@gmail.com")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.detailInfo.p", is("1234")));
    }

    @Test
    void _04_한글멤버하나조회() throws Exception {
        long id = 3L;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/member/search/" + id))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.name", is("한글테스트")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.detailInfo.p", is("한글테스트")));
    }
}