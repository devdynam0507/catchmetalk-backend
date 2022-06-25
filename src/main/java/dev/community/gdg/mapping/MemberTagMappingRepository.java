package dev.community.gdg.mapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTagMappingRepository extends JpaRepository<MemberTagMapping, Long> {
}
