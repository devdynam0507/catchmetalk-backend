package dev.community.gdg.chatting.room.dto;

import dev.community.gdg.chatting.message.dto.MessageResponse;
import lombok.Data;

import java.util.List;

@Data
public class ChattingRoomResponse {
    private Long id;
    private List<MessageResponse> messages;
}
