package dev.community.gdg.chatting.room.service;

import dev.community.gdg.chatting.room.domain.ChattingRoom;
import dev.community.gdg.chatting.room.domain.ChattingRoomRepository;
import dev.community.gdg.chatting.room.dto.ChatRoomSpecification;
import dev.community.gdg.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChattingRoomService {
    private final MemberRepository memberRepository;
    private final ChattingRoomRepository chattingRoomRepository;

    public ChatRoomSpecification getOrCreateRoom(Long senderMemberId, Long receiverMemberId) {
        ChattingRoom chattingRoom = findChattingRoom(senderMemberId, receiverMemberId)
                .orElseGet(() -> chattingRoomRepository.save(
                        ChattingRoom.of(
                                memberRepository.findById(senderMemberId).orElse(null),
                                memberRepository.findById(receiverMemberId).orElse(null)
                        )
                ));
        return ChatRoomSpecification.from(chattingRoom);
    }

    private Optional<ChattingRoom> findChattingRoom(Long senderMemberId, Long receiverMemberId) {
        Optional<ChattingRoom> chattingRoomOptional1 = chattingRoomRepository.findByFirstMember_idAndSecondMember_id(senderMemberId, receiverMemberId);
        if (chattingRoomOptional1.isPresent()) {
            return chattingRoomOptional1;
        }
        Optional<ChattingRoom> chattingRoomOptional2 = chattingRoomRepository.findByFirstMember_idAndSecondMember_id(receiverMemberId, senderMemberId);
        return chattingRoomOptional2;
    }

    public List<ChatRoomSpecification> getChatRooms(Long memberId) {
        return chattingRoomRepository.findByFirstMember_idOrSecondMember_id(memberId, memberId)
                .stream()
                .map(ChatRoomSpecification::from)
                .collect(Collectors.toList());
    }
}
