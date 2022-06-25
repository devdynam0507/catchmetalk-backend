package dev.community.gdg.member.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dev.community.gdg.mapping.MemberTagMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.Nullable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id = 0L;

	private String nickname;

	@Nullable
	private Double latitude = null;

	@Nullable
	private Double longitude = null;

	@CreatedDate
    private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<MemberTagMapping> memberTagMappings = new ArrayList<>();

	public Member(final String nickname) {
	    this.nickname = nickname;
	    this.createdAt = LocalDateTime.now();
	    this.updatedAt = LocalDateTime.now();
	}

	public void addMemberTagMapping(final MemberTagMapping mapping) {
		memberTagMappings.add(mapping);
	}

	public void setMemberTagMappings(List<MemberTagMapping> memberTagMappings) {
		this.memberTagMappings = memberTagMappings;
	}

	public void setLatitude(@Nullable Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(@Nullable Double longitude) {
		this.longitude = longitude;
	}

}
