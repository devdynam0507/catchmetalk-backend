package dev.community.gdg.chatting.room.dto;

import dev.community.gdg.chatting.room.domain.ChattingRoom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomSpecification {
    private Long id;
    // TODO: 마지막 메시지

    public static ChatRoomSpecification from(ChattingRoom chattingRoom) {
        ChatRoomSpecification chatRoomSpecification = new ChatRoomSpecification();
        chatRoomSpecification.id = chattingRoom.getId();
        return chatRoomSpecification;
    }
}
