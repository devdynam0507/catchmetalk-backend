package dev.community.gdg.chatting.message.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import dev.community.gdg.chatting.room.domain.ChattingRoom;
import dev.community.gdg.member.domain.Member;
import lombok.Getter;

@Entity
@Getter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "chattingRoomId")
    private ChattingRoom chattingRoom;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private Member sender;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private Member receiver;

    public static Message of(final ChattingRoom chattingRoom, final Member sender,
        final Member receiver, final String content) {
        Message message = new Message();
        message.chattingRoom = chattingRoom;
        message.sender = sender;
        message.receiver = receiver;
        message.content = content;
        return message;
    }

}
