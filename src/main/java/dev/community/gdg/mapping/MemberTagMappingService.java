package dev.community.gdg.mapping;

import dev.community.gdg.member.domain.Member;
import dev.community.gdg.tag.domain.Tag;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberTagMappingService implements MappingService<Member, Tag, MemberTagMapping> {

	@Override
	public MemberTagMapping mapping(Member map1, Tag map2) {
		final MemberTagMapping mapping = new MemberTagMapping(map1, map2);
		map1.addMemberTagMapping(mapping);
		map2.addMemberTagMapping(mapping);

		return new MemberTagMapping(map1, map2);
	}

}
