package dev.community.gdg.member.service;

import dev.community.gdg.configuration.jwt.JwtService;
import dev.community.gdg.member.dto.MemberCreateRequest;
import dev.community.gdg.member.dto.MemberCreateResponse;
import dev.community.gdg.member.dto.MemberSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

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
                                    .nickname(createRandomNickname())
                                    .uuid(uuid)
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

    private String createRandomNickname() {
        List<String> candidates = Arrays.asList(
                "쿠글",
                "양파마켓",
                "오늘의꿈",
                "마켓킬러"
        );
        int randomIndex = ThreadLocalRandom.current().nextInt(0, candidates.size());
        String company = candidates.get(randomIndex);
        String number = String.valueOf(ThreadLocalRandom.current().nextInt(0, 10000));
        return company + number;
    }
}
