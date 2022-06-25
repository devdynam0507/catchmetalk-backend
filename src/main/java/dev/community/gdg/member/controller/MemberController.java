package dev.community.gdg.member.controller;

import dev.community.gdg.common.CommonResponse;
import dev.community.gdg.common.MemberIdResolver;
import dev.community.gdg.common.ResultCode;

import dev.community.gdg.member.domain.Member;
import dev.community.gdg.member.domain.MemberRepository;
import dev.community.gdg.member.dto.MemberCreateRequest;
import dev.community.gdg.member.dto.MemberCreateResponse;

import dev.community.gdg.member.dto.LoginRequest;

import dev.community.gdg.member.dto.MemberSpecification;
import dev.community.gdg.member.dto.MemberUpdateCoordinateRequest;
import dev.community.gdg.member.dto.MemberUpdateSpecification;
import dev.community.gdg.member.service.LoginService;
import dev.community.gdg.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    private final LoginService loginService;
    private final MemberIdResolver memberIdResolver;

    @GetMapping("/me")
    public CommonResponse<MemberSpecification> getMemberInformationMe(
            @ApiIgnore Principal principal
    ) {
        final Long memberId = memberIdResolver.resolveMemberId(principal);
        final MemberSpecification memberSpecification = memberService.getMemberInformationMe(memberId);
        return CommonResponse.success(memberSpecification, ResultCode.SUCCESS);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<?>> login(LoginRequest loginRequest) {
        String accessToken = loginService.login(loginRequest.getUuid());
        return ResponseEntity.ok(CommonResponse.success(accessToken));
    }

    @PutMapping("/me")
    public CommonResponse<MemberUpdateSpecification> modifyUserInformation(
            @RequestBody final MemberUpdateSpecification updateSpecification,
			@ApiIgnore Principal principal
    ) {
        final Long memberId = memberIdResolver.resolveMemberId(principal);
        final MemberUpdateSpecification updatedSpecification = memberService.updateMember(memberId, updateSpecification);
        return CommonResponse.success(updatedSpecification, ResultCode.SUCCESS);
    }

	@PutMapping("/me/coordinate")
	public CommonResponse<MemberUpdateSpecification> modifyMyCoordinate(
	    @RequestBody MemberUpdateCoordinateRequest coordinateUpdateRequest,
        @ApiIgnore Principal principal
    ) {
        final Long memberId = memberIdResolver.resolveMemberId(principal);
        final MemberUpdateSpecification updatedSpecification =
            memberService.updateMemberCoordinateOnly(memberId, coordinateUpdateRequest);
		return CommonResponse.success(updatedSpecification, ResultCode.SUCCESS);
	}

	@GetMapping("/search")
	public CommonResponse<List<MemberSpecification>> getNearByUsers() {
        final Long id = 1L;
        final List<MemberSpecification> users = memberService.getNearbyUsers(id);
		return CommonResponse.success(users, ResultCode.SUCCESS);
	}

}
