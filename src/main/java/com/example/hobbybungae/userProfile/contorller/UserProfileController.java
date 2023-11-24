package com.example.hobbybungae.userProfile.contorller;

import com.example.hobbybungae.userProfile.dto.UserProfileResponseDto;
import com.example.hobbybungae.userProfile.dto.UserProfileUpdateRequestDto;
import com.example.hobbybungae.userProfile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby-bungae/v1/users/profile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    // 프로필 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponseDto> getUser (
            @PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        UserProfileResponseDto responseDto = userProfileService.getUser(userId, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    // 프로필 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<UserProfileResponseDto> updateUser(
            @PathVariable Long userId, @RequestBody UserProfileUpdateRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        UserProfileResponseDto responseDto = userProfileService.updateUser(userId, requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }
}
