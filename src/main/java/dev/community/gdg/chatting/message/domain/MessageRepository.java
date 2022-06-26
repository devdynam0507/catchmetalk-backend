package dev.community.gdg.chatting.message.domain;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySender_idOrReceiver_id(Long firstMemberId, Long secondMemberId);

    List<Message> findByChattingRoom_id(Pageable pageable, Long chattingRoomId);

}
