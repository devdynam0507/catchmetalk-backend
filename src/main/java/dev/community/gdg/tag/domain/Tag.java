package dev.community.gdg.tag.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dev.community.gdg.mapping.MemberTagMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = 0L;

	private String tagName;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MemberTagMapping> memberTagMappings = new ArrayList<>();

	public Tag(final String tagName) {
		this.tagName = tagName;
	}

	public void addMemberTagMapping(final MemberTagMapping mapping) {
	    memberTagMappings.add(mapping);
    }

    public void setMemberTagMappings(List<MemberTagMapping> memberTagMappings) {
        this.memberTagMappings = memberTagMappings;
    }

}
