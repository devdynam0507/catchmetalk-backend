package dev.community.gdg.tag.controller;

import java.util.List;

import dev.community.gdg.common.CommonResponse;
import dev.community.gdg.common.ResultCode;
import dev.community.gdg.tag.domain.Tag;
import dev.community.gdg.tag.dto.TagResponse;
import dev.community.gdg.tag.dto.TagSaveRequest;
import dev.community.gdg.tag.service.TagService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/tags")
@RestController
@RequiredArgsConstructor
public class TagController {

	private final TagService tagService;

	@GetMapping
	public ResponseEntity<CommonResponse<List<TagResponse>>> getAllTags() {
		final List<TagResponse> tags = tagService.getTags();
		final CommonResponse<List<TagResponse>> response = CommonResponse.success(tags,
			ResultCode.SUCCESS);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<CommonResponse<TagResponse>> saveTag(
			@RequestBody final TagSaveRequest tagSaveRequest) {
		final TagResponse saved = tagService.saveTag(tagSaveRequest);
		final CommonResponse<TagResponse> response = CommonResponse.success(saved,
			ResultCode.SUCCESS);
		return ResponseEntity.ok(response);
	}

}
