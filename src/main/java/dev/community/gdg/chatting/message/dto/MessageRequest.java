package dev.community.gdg.chatting.message.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {
    private String content;
}
