package dev.community.gdg.tag.service;

import java.util.List;
import java.util.stream.Collectors;

import dev.community.gdg.tag.domain.Tag;
import dev.community.gdg.tag.domain.TagRepository;
import dev.community.gdg.tag.dto.TagResponse;
import dev.community.gdg.tag.dto.TagSaveRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

	private final TagRepository tagRepository;

	public List<TagResponse> getTags() {
		return tagRepository.findAll().stream()
			.map(tag -> TagResponse.builder()
				.id(tag.getId())
				.tagName(tag.getTagName())
				.build())
			.collect(Collectors.toList());
	}

	public TagResponse saveTag(final TagSaveRequest tagSaveRequest) {
		final Tag saved = tagRepository.save(tagSaveRequest.toEntity());
		return TagResponse.builder()
			.id(saved.getId())
			.tagName(tagSaveRequest.getTagName())
			.build();
	}

}
