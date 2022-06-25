package dev.community.gdg.member.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sun.istack.Nullable;
import dev.community.gdg.mapping.MemberTagMappingService;
import dev.community.gdg.member.domain.Member;
import dev.community.gdg.member.domain.MemberRepository;
import dev.community.gdg.member.dto.MemberCreateRequest;
import dev.community.gdg.member.dto.MemberCreateResponse;
import dev.community.gdg.member.dto.MemberSpecification;
import dev.community.gdg.member.dto.MemberUpdateCoordinateRequest;
import dev.community.gdg.member.dto.MemberUpdateSpecification;
import dev.community.gdg.tag.domain.Tag;
import dev.community.gdg.tag.domain.TagRepository;
import dev.community.gdg.tag.dto.TagResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final TagRepository tagRepository;

    private final MemberTagMappingService memberTagMappingService;

    public MemberCreateResponse createMember(final MemberCreateRequest createRequest) {
        final List<Tag> tags = tagRepository.findAllById(createRequest.getTagIds());
        final Member newMember = new Member(createRequest.getNickname());
        tags.stream().map(tag -> memberTagMappingService.mapping(newMember, tag))
            .collect(Collectors.toList());

        final Member createdMember = memberRepository.save(newMember);
        // TODO: 예외처리 해야함.
        final List<TagResponse> createdTags = unwrapTags(createdMember);
        return MemberCreateResponse.builder()
            .id(createdMember.getId())
            .nickname(createdMember.getNickname())
            .tags(createdTags)
            .build();
    }

    public MemberSpecification getMemberInformationMe(final Long id) {
        final Optional<Member> memberOptional = memberRepository.findById(id);
        final Member member = memberOptional.orElseThrow(() ->
            new IllegalArgumentException("member is not found"));

        return MemberSpecification.builder()
            .id(member.getId())
            .nickname(member.getNickname())
            .tags(unwrapTags(member))
            .build();
    }

    public MemberUpdateSpecification updateMember(final MemberUpdateSpecification updateSpecification) {
        // TODO: 중복 코드
        final Optional<Member> memberOptional = memberRepository.findById(updateSpecification.getId());
        final Member member = memberOptional.orElseThrow(() ->
            new IllegalArgumentException("member is not found"));
        member.setLatitude(updateSpecification.getLatitude());
        member.setLongitude(updateSpecification.getLongitude());
        member.setNickname(updateSpecification.getNickname());
        final Member updatedMember = memberRepository.save(member);

        return MemberUpdateSpecification.builder()
            .id(updatedMember.getId())
            .nickname(updatedMember.getNickname())
            .latitude(updatedMember.getLatitude())
            .longitude(updatedMember.getLongitude())
            .build();
    }

    public MemberUpdateSpecification updateMemberCoordinateOnly(
        final Long id, final MemberUpdateCoordinateRequest coordinateUpdateRequest) {
        // TODO: 중복 코드
        final Optional<Member> memberOptional = memberRepository.findById(id);
        final Member member = memberOptional.orElseThrow(() ->
            new IllegalArgumentException("member is not found"));
        member.setLatitude(coordinateUpdateRequest.getLatitude());
        member.setLongitude(coordinateUpdateRequest.getLongitude());
        final Member updatedMember = memberRepository.save(member);

        return MemberUpdateSpecification.builder()
            .id(updatedMember.getId())
            .nickname(updatedMember.getNickname())
            .latitude(updatedMember.getLatitude())
            .longitude(updatedMember.getLongitude())
            .build();
    }

    private List<TagResponse> unwrapTags(final Member member) {
        return member.getMemberTagMappings().stream()
            .map(mappingTable -> {
                final Tag tag = mappingTable.getTag();
                return TagResponse.builder()
                    .id(tag.getId())
                    .tagName(tag.getTagName())
                    .build();
            })
            .collect(Collectors.toList());
    }

}
