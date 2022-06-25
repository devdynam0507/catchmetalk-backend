package dev.community.gdg.member.dto;

import java.util.List;

import dev.community.gdg.tag.dto.TagResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberCreateResponse {

    private Long id;
    private String nickname;
    private List<TagResponse> tags;

}
