package dev.community.gdg.member.service;

import java.util.Optional;

import dev.community.gdg.member.domain.Member;
import dev.community.gdg.member.domain.MemberRepository;
import dev.community.gdg.member.dto.MemberSpecification;
import dev.community.gdg.member.dto.MemberUpdateSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Test
    void getMemberInformationMeSuccess() {
        final Long id = 1L;
        final Member member = new Member("test-user");

        when(memberRepository.findById(anyLong()))
            .thenReturn(Optional.of(member));
        final MemberSpecification find = memberService.getMemberInformationMe(id);

        assertThat(find.getNickname()).isEqualTo("test-user");
    }

    @Test
    void getMemberInformationMeIfNull() {
        final Long id = 1L;

        when(memberRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> memberService.getMemberInformationMe(id))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updateMemberTest() {
        final MemberUpdateSpecification memberUpdateSpecification = MemberUpdateSpecification.builder()
            .id(1L)
            .nickname("test-user2")
            .latitude(127.00001)
            .longitude(64.00001)
            .build();

        final Member returnTarget = new Member("test-user");
        returnTarget.setId(1L);
        when(memberRepository.findById(anyLong()))
            .thenReturn(Optional.of(returnTarget));
        when(memberRepository.save(any()))
            .thenReturn(returnTarget);
        final MemberUpdateSpecification resultUpdateSpec =
            memberService.updateMember(1L, memberUpdateSpecification);

        assertThat(resultUpdateSpec.getNickname()).isEqualTo("test-user2");
        assertThat(resultUpdateSpec.getLatitude()).isEqualTo(127.00001);
        assertThat(resultUpdateSpec.getLongitude()).isEqualTo(64.00001);
    }
}