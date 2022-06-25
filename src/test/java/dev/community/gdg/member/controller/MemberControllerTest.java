package dev.community.gdg.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.*;

import java.util.Collections;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.community.gdg.member.dto.MemberCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @DisplayName("유저 생성 api 호출 테스트")
    @Test
    void createMemberTest() throws Exception {
        final MemberCreateRequest memberCreateRequest = MemberCreateRequest.builder()
            .nickname("test-user")
            .tagIds(Collections.emptyList())
            .build();
        final String requestBody = objectMapper.writeValueAsString(memberCreateRequest);

        final ResultActions resultActions = mockMvc.perform(post("/v1/members/me")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody));

        resultActions.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.id").value("1"))
            .andExpect(jsonPath("$.data.nickname").value("test-user"));
    }

}