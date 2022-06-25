package dev.community.gdg.chatting.room.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomRequest {
    private Long receiverMemberId;
}
