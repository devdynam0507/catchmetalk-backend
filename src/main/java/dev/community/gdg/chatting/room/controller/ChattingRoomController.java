package dev.community.gdg.chatting.room.controller;

import dev.community.gdg.chatting.room.dto.ChattingRoomResponse;
import dev.community.gdg.chatting.room.dto.CreateRoomRequest;
import dev.community.gdg.chatting.room.service.ChattingRoomService;
import dev.community.gdg.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/chat-rooms")
@RequiredArgsConstructor
public class ChattingRoomController {
    private final ChattingRoomService chattingRoomService;

    @PostMapping
    public CommonResponse<ChattingRoomResponse> getOrCreateRoom(
            @RequestBody CreateRoomRequest createRoomRequest
    ) {
        return CommonResponse.success();
    }
}
