package com.example.hobbybungae.userProfile.service;

import com.example.hobbybungae.domain.User;
import com.example.hobbybungae.userProfile.dto.UserProfileResponseDto;
import com.example.hobbybungae.userProfile.dto.UserProfileUpdateRequestDto;
import com.example.hobbybungae.userProfile.exception.NotMatchingIdException;
import com.example.hobbybungae.userProfile.exception.NotMatchingPasswordException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    // 사용자 프로필 조회
    public UserProfileResponseDto getUser(Long userId , User inputUser) {
        if (!inputUser.getIdName().equals(userId)) {
            throw new NotMatchingIdException();
        }
        User user = getUserEntity(userId);
        return new UserProfileResponseDto(user);
    }

    // Update Password
    @Transactional
    public UserProfileResponseDto updateUser(Long userId, UserProfileUpdateRequestDto requestDto, User inputUser) {
        if (!inputUser.getIdName().equals(userId)) {
            throw new NotMatchingIdException();
        }
        if (!requestDto.getPassword().isEmpty()) {
            String inputPassword = requestDto.getPasswordReconfirm();
            if(!passwordEncoder.matches(inputPassword, inputUser.getPassword())) {
                throw new NotMatchingPasswordException();
            }
            inputUser.updatePassword(requestDto);
        }
        inputUser.update(requestDto);
        return new UserProfileResponseDto(inputUser);
    }

    @Transactional(readOnly = true)
    private User getUserEntity(Long userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new userNotFoundException("해당 프로필을 찾을 수 없습니다."));
    }
}

