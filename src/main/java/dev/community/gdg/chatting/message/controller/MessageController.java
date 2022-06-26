package dev.community.gdg.chatting.message.controller;

import dev.community.gdg.chatting.message.dto.MessageRequest;
import dev.community.gdg.chatting.message.dto.MessageResponse;
import dev.community.gdg.chatting.message.service.MessageService;
import dev.community.gdg.common.CommonResponse;
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
    private final MessageService messageService;

    @GetMapping
    public CommonResponse<List<MessageResponse>> getMessages(
            @ApiIgnore Principal principal,
         @PathVariable(value = "chattingRoomId") Long chattingRoomId
    ) {
        Long memberId = memberIdResolver.resolveMemberId(principal);
        return CommonResponse.success(messageService.getMessages(memberId, chattingRoomId));
    }

    @PostMapping
    public CommonResponse<MessageResponse> sendMessage(
            @RequestBody MessageRequest messageRequest,
            @ApiIgnore Principal principal,
            @PathVariable(value = "chattingRoomId") Long chattingRoomId
    ) {
        Long memberId = memberIdResolver.resolveMemberId(principal);
        messageService.sendMessage(messageRequest, memberId, chattingRoomId);
        return CommonResponse.success();
    }
}
