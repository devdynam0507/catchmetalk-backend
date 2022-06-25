package dev.community.gdg.chatting.message.dto;

import dev.community.gdg.member.dto.MemberSpecification;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageResponse {
    private Long id;
    private Long roomId;
    private MemberSpecification sender;
    private MemberSpecification receiver;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean amISender;
}
