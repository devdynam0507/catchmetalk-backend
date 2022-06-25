package dev.community.gdg.member.controller;

import java.util.Collections;
import java.util.List;

import dev.community.gdg.common.CommonResponse;
import dev.community.gdg.common.ResultCode;
import dev.community.gdg.member.dto.MemberCreateRequest;
import dev.community.gdg.member.dto.MemberCreateResponse;
import dev.community.gdg.member.dto.MemberSpecification;
import dev.community.gdg.member.dto.MemberUpdateCoordinateRequest;
import dev.community.gdg.member.dto.MemberUpdateSpecification;
import dev.community.gdg.member.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/me")
    public CommonResponse<MemberCreateResponse> createMember(
        @RequestBody final MemberCreateRequest createMemberRequest) {
        final MemberCreateResponse response = memberService.createMember(createMemberRequest);
        return CommonResponse.success(response, ResultCode.SUCCESS);
    }

	@GetMapping("/me")
	public CommonResponse<MemberSpecification> getMemberInformationMe() {
        final Long id = 0L;
        final MemberSpecification memberSpecification = memberService.getMemberInformationMe(id);
		return CommonResponse.success(memberSpecification, ResultCode.SUCCESS);
	}

	// TODO: 로그인 서비스 붙여야함.
	@PostMapping("/login")
	public ResponseEntity<CommonResponse<?>> login() {
		return ResponseEntity.ok(null);
	}

	@PutMapping("/me")
	public CommonResponse<MemberUpdateSpecification> modifyUserInformation(
	    @RequestBody final MemberUpdateSpecification updateSpecification) {
        final MemberUpdateSpecification updatedSpecification =
            memberService.updateMember(updateSpecification);
		return CommonResponse.success(updatedSpecification, ResultCode.SUCCESS);
	}

	@PutMapping("/me/coordinate")
	public CommonResponse<MemberUpdateSpecification> modifyMyCoordinate(
	    @RequestBody MemberUpdateCoordinateRequest coordinateUpdateRequest) {
        final Long id = 0L; // TODO: 플레이어 아이디 넣어줘야 합니다. (유저 현 좌표 정보 변경)
        final MemberUpdateSpecification updatedSpecification =
            memberService.updateMemberCoordinateOnly(id, coordinateUpdateRequest);
		return CommonResponse.success(updatedSpecification, ResultCode.SUCCESS);
	}

	@GetMapping("/search")
	public CommonResponse<List<MemberSpecification>> getNearByUsers() {
		return CommonResponse.success(Collections.emptyList(), ResultCode.SUCCESS);
	}

}
