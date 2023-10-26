package org.navistack.smoketest.web;

import org.junit.jupiter.api.Test;
import org.navistack.framework.core.error.UserErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SampleWebApplicationTest {
    private final MockMvc mockMvc;

    @Autowired
    SampleWebApplicationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testGetEcho() throws Exception {
        mockMvc.perform(get("/echo?content={content}", "Hello world"))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.succeeded", is(true)))
                .andExpect(jsonPath("$.result.content", is("Hello world")));
    }

    @Test
    void testPostEcho() throws Exception {
        mockMvc.perform(post("/echo").param("content", "Hello world"))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.succeeded", is(true)))
                .andExpect(jsonPath("$.result.content", is("Hello world")));
    }

    @Test
    void testEchoWithoutContent() throws Exception {
        mockMvc.perform(get("/echo"))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.succeeded", is(false)))
                .andExpect(jsonPath("$.error", is(UserErrorCodes.MISSING_PARAMETER)));
    }

    @Test
    void testPing() throws Exception {
        mockMvc.perform(get("/ping"))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)));
    }
}
