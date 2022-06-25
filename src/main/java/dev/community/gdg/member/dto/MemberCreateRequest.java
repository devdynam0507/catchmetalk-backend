package dev.community.gdg.member.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSaveRequest {

	private String nickname;

	private List<Long> tagIds;

}
