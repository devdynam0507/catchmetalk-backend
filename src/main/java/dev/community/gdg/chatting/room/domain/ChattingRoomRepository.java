package dev.community.gdg.chatting.room.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
    List<ChattingRoom> findByFirstMember_idOrSecondMember_id(Long firstMemberId, Long secondMemberId);

    Optional<ChattingRoom> findByFirstMember_idAndSecondMember_id(Long firstMemberId, Long secondMemberId);
}
