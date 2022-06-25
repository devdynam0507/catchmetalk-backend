package dev.community.gdg.tag.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TagResponse {

	private Long id;
	private String tagName;

}
