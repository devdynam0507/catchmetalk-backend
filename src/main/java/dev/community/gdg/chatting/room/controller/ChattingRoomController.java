package dev.community.gdg.chatting.room.controller;

import dev.community.gdg.chatting.room.dto.ChatRoomSpecification;
import dev.community.gdg.chatting.room.dto.CreateRoomRequest;
import dev.community.gdg.chatting.room.service.ChattingRoomService;
import dev.community.gdg.common.CommonResponse;
import dev.community.gdg.common.MemberIdResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RestController
@RequestMapping("/v1/chat-rooms")
@RequiredArgsConstructor
public class ChattingRoomController {
    private final ChattingRoomService chattingRoomService;
    private final MemberIdResolver memberIdResolver;

    @PostMapping
    public CommonResponse<ChatRoomSpecification> getOrCreateRoom(
            @RequestBody CreateRoomRequest createRoomRequest,
            @ApiIgnore Principal principal
    ) {
        Long memberId = memberIdResolver.resolveMemberId(principal);
        ChatRoomSpecification chatRoomSpecification = chattingRoomService.getOrCreateRoom(
                memberId,
                createRoomRequest.getReceiverMemberId()
        );
        return CommonResponse.success(chatRoomSpecification);
    }
}
