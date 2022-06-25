package dev.community.gdg.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberUpdateCoordinateRequest {

    private double latitude;
    private double longitude;

}
