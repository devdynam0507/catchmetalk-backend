package dev.community.gdg.member.controller;

import dev.community.gdg.common.CommonResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/members")
public class MemberController {

	@GetMapping("/me")
	public ResponseEntity<CommonResponse<?>> getUserInformation() {
		return ResponseEntity.ok(null);
	}

	@PostMapping("/login")
	public ResponseEntity<CommonResponse<?>> login() {
		return ResponseEntity.ok(null);
	}

	@PutMapping("/me")
	public ResponseEntity<CommonResponse<?>> modifyUserInformation() {
		return ResponseEntity.ok(null);
	}

	@PutMapping("/me/coordinate")
	public ResponseEntity<CommonResponse<?>> modifyMyCoordinate(@RequestBody Object obj) {
		return ResponseEntity.ok(null);
	}

	@GetMapping("/search")
	public ResponseEntity<CommonResponse<?>> getNearByUsers() {
		return ResponseEntity.ok(null);
	}

}
