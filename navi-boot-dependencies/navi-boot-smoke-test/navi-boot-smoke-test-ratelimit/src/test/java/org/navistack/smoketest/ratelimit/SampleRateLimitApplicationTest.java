package org.navistack.smoketest.ratelimit;

import org.junit.jupiter.api.Test;
import org.navistack.framework.core.error.UserErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SampleRateLimitApplicationTest {
    private final MockMvc mockMvc;

    @Autowired
    SampleRateLimitApplicationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testSlidingWindowRateLimitedPing() throws Exception {
        mockMvc.perform(get("/rate-limited/sliding-window/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)));
        mockMvc.perform(get("/rate-limited/sliding-window/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.TOO_MANY_REQUESTS.value()))
                .andExpect(jsonPath("$.succeeded", is(false)))
                .andExpect(jsonPath("$.error", is(UserErrorCodes.FREQUENT_REQUEST)));
        Thread.sleep(2000);
        mockMvc.perform(get("/rate-limited/sliding-window/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)));
    }

    @Test
    void testFixedWindowRateLimitedPing() throws Exception {
        mockMvc.perform(get("/rate-limited/fixed-window/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)));
        mockMvc.perform(get("/rate-limited/fixed-window/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.TOO_MANY_REQUESTS.value()))
                .andExpect(jsonPath("$.succeeded", is(false)))
                .andExpect(jsonPath("$.error", is(UserErrorCodes.FREQUENT_REQUEST)));
        Thread.sleep(1000);
        mockMvc.perform(get("/rate-limited/fixed-window/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)));
    }
}
