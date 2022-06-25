package dev.community.gdg.tag.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.community.gdg.tag.dto.TagSaveRequest;
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
class TagControllerTest {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	MockMvc mockMvc;

	@DisplayName("태그 데이터가 제대로 저장이 되는지 테스트")
	@Test
	void saveTagTest() throws Exception {
		final TagSaveRequest request = TagSaveRequest.builder()
			.tagName("test-tag")
			.build();
		final String requestBody = objectMapper.writeValueAsString(request);

		final ResultActions resultActions = mockMvc.perform(post("/v1/tags")
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody));

		resultActions.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.tagName").value("test-tag"));
	}

}