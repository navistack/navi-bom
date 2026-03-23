package org.navistack.smoketest.captcha;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.navistack.framework.core.error.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SampleCaptchaApplicationTest {

    private final MockMvc mockMvc;

    @Autowired
    SampleCaptchaApplicationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void shouldReturnSuccessWhenValidCaptchaTicketIsProvided() throws Exception {
        MvcResult challengeResult = mockMvc.perform(post("/captcha/challenge"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)))
                .andExpect(jsonPath("$.result", instanceOf(String.class)))
                .andReturn();
        String challengeKey = JsonPath.read(challengeResult.getResponse().getContentAsString(), "$.result");

        MvcResult answerResult = mockMvc.perform(
                        post("/captcha/answer")
                                .param("challengeId", challengeKey)
                                .param("answer", "FIXED")
                ).andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)))
                .andExpect(jsonPath("$.result.validated", is(true)))
                .andExpect(jsonPath("$.result.ticket", instanceOf(String.class)))
                .andReturn();
        String ticket = JsonPath.read(answerResult.getResponse().getContentAsString(), "$.result.ticket");

        mockMvc.perform(get("/ping").param("ticket", ticket))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.succeeded", is(true)));
    }

    @Test
    void shouldReturnBadRequestWhenTicketIsMissing() throws Exception {
        mockMvc.perform(get("/ping"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.succeeded", is(false)))
                .andExpect(jsonPath("$.error", is(ErrorCodes.CAPTCHA_TEST_FAILED)));
    }

    @Test
    void shouldReturnBadRequestWhenTicketIsInvalid() throws Exception {
        mockMvc.perform(get("/ping").param("ticket", "76e53192-ea43-4219-be41-d8d5b3798907"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.succeeded", is(false)))
                .andExpect(jsonPath("$.error", is(ErrorCodes.CAPTCHA_TEST_FAILED)));
    }
}
