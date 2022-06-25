package dev.community.gdg.member.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberCreateRequest {

	private String nickname;

	private String uuid;

	private List<Long> tagIds;
}
