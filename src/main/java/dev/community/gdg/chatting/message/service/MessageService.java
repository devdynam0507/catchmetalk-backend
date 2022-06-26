package dev.community.gdg.chatting.message.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.community.gdg.chatting.message.domain.Message;
import dev.community.gdg.chatting.message.domain.MessageRepository;
import dev.community.gdg.chatting.message.dto.MessageRequest;
import dev.community.gdg.chatting.message.dto.MessageResponse;
import dev.community.gdg.chatting.room.domain.ChattingRoom;
import dev.community.gdg.chatting.room.domain.ChattingRoomRepository;
import dev.community.gdg.chatting.room.service.ChattingRoomService;
import dev.community.gdg.member.domain.Member;
import dev.community.gdg.member.domain.MemberRepository;
import dev.community.gdg.member.dto.MemberSpecification;
import dev.community.gdg.member.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberService memberService;
    private final ChattingRoomService chattingRoomService;
    private final ChattingRoomRepository chattingRoomRepository;
    private final MemberRepository memberRepository;

    public List<MessageResponse> getMessages(final Long memberId, final Long chatroomId) {
        final List<Message> messages = messageRepository.findByChattingRoom_id(PageRequest.of(0, 100),chatroomId);
        return messages.stream().map(i -> {
            ChattingRoom chattingRoom = i.getChattingRoom();
            boolean isSender = i.getSender().getId().equals(chatroomId);
            MessageResponse r = new MessageResponse();
            r.setAmISender(isSender);
            r.setContent(i.getContent());
            r.setRoomId(chattingRoom.getId());
            r.setSender(MemberSpecification.from(i.getSender()));
            r.setReceiver(MemberSpecification.from(i.getReceiver()));
            r.setId(i.getId());
            return r;
      }).collect(Collectors.toList());
    }

    public void sendMessage(final MessageRequest messageRequest, final Long memberId,
        final Long chattingRoomId) {
        final Optional<ChattingRoom> chattingRoomOptional =
            chattingRoomRepository.findById(chattingRoomId);
        final Optional<Member> memberOptional =
            memberRepository.findById(memberId);
        final Member member = memberOptional.orElseThrow(() -> new IllegalArgumentException("user not found"));
        final ChattingRoom chattingRoom = chattingRoomOptional
            .orElseThrow(() -> new IllegalArgumentException("room is null"));
        final Member receiver = chattingRoom.getFirstMember().getId().equals(memberId) ?
            chattingRoom.getFirstMember() : chattingRoom.getSecondMember();

        Message message = Message.of(chattingRoom, member, receiver, messageRequest.getContent());
        messageRepository.save(message);
    }
}
