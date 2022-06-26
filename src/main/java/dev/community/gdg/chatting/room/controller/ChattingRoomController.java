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
import java.util.List;

@RestController
@RequestMapping("/v1/chat-rooms")
@RequiredArgsConstructor
public class ChattingRoomController {
    private final ChattingRoomService chattingRoomService;
    private final MemberIdResolver memberIdResolver;

    @GetMapping
    public CommonResponse<List<ChatRoomSpecification>> getChatRooms(
            @ApiIgnore Principal principal
    ) {
        Long memberId = memberIdResolver.resolveMemberId(principal);
        List<ChatRoomSpecification> chatRoomSpecifications = chattingRoomService.getChatRooms(memberId);
        return CommonResponse.success(chatRoomSpecifications);
    }

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
