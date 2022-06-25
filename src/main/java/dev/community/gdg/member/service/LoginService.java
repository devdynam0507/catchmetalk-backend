package dev.community.gdg.member.service;

import dev.community.gdg.configuration.jwt.JwtService;
import dev.community.gdg.member.dto.MemberCreateRequest;
import dev.community.gdg.member.dto.MemberCreateResponse;
import dev.community.gdg.member.dto.MemberSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberService memberService;
    private final JwtService jwtService;

    public String login(String uuid) {
        MemberSpecification memberSpecification = memberService.getMemberInformationMe(uuid)
                .orElseGet(() -> {
                    MemberCreateResponse memberCreateResponse = memberService.createMember(
                            MemberCreateRequest.builder()
                                    .nickname("nickname")
                                    .tagIds(Collections.emptyList())
                                    .build()
                    );
                    return MemberSpecification.builder()
                            .id(memberCreateResponse.getId())
                            .nickname(memberCreateResponse.getNickname())
                            .tags(memberCreateResponse.getTags())
                            .build();
                });
        return jwtService.encode(memberSpecification.getId());
    }
}
