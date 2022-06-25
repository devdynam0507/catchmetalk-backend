package dev.community.gdg.tag.dto;

import dev.community.gdg.tag.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagSaveRequest {

	private String tagName;

	public Tag toEntity() {
		return new Tag(tagName);
	}

}
