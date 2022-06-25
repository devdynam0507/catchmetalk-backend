package dev.community.gdg.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberUpdateSpecification {

    private Long id;
    private String nickname;
    private Double latitude;
    private Double longitude;

}
