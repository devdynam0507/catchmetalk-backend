package dev.community.gdg.member.dto;

import lombok.Data;

@Data(staticConstructor = "from")
public class LoginResponse {
    private final String accessToken;
}
