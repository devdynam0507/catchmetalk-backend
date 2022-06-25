package dev.community.gdg.chatting.room.controller;

import dev.community.gdg.chatting.room.dto.ChattingRoomResponse;
import dev.community.gdg.common.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/chat-rooms")
public class RoomController {
    @GetMapping
    public CommonResponse<ChattingRoomResponse> getOrCreateRoom() {
        return CommonResponse.success();
    }
}
