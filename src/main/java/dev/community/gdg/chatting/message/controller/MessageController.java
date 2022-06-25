package dev.community.gdg.chatting.message.controller;

import dev.community.gdg.chatting.message.dto.MessageRequest;
import dev.community.gdg.chatting.message.dto.MessageResponse;
import dev.community.gdg.common.MemberIdResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/chat-rooms/{chattingRoomId}/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MemberIdResolver memberIdResolver;

    @GetMapping
    public List<MessageResponse> getMessages(
            @ApiIgnore Principal principal
    ) {
        Long memberId = memberIdResolver.resolveMemberId(principal);
        return Collections.emptyList();
    }

    @PostMapping
    public MessageResponse sendMessage(
            @RequestBody MessageRequest messageRequest,
            @ApiIgnore Principal principal
    ) {
        Long memberId = memberIdResolver.resolveMemberId(principal);
        return null;
    }
}
