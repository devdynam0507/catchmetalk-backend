package dev.community.gdg.member.dto;

import java.util.List;
import java.util.stream.Collectors;

import dev.community.gdg.member.domain.Member;
import dev.community.gdg.tag.domain.Tag;
import dev.community.gdg.tag.dto.TagResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberSpecification {

    private final Long id;
    private final String nickname;
    private final List<TagResponse> tags;

    public static MemberSpecification from(Member member) {
        return MemberSpecification.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .tags(unwrapTags(member))
                .build();
    }

    private static List<TagResponse> unwrapTags(final Member member) {
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
