package dev.community.gdg.member.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT id, (6371 * acos( cos( radians( :latitude ) ) * cos( radians( latitude ) ) * cos( radians(longitude) - radians( :longitude ) ) + sin( radians( :latitude ) ) * sin( radians(latitude) ))) as distance1"
        + " FROM member"
        + " GROUP BY id HAVING distance1 <= :distance", nativeQuery = true)
    List<Member> findNearbyMembers(@Param("latitude") final Double latitude,
                                   @Param("longitude") final Double longitude,
                                   @Param("distance") final Double distance);

}
