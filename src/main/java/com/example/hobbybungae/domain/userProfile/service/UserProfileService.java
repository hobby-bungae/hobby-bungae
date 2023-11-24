package com.example.hobbybungae.domain.userProfile.service;

import com.example.hobbybungae.domain.user.entity.User;
import com.example.hobbybungae.domain.user.repository.UserRepository;
import com.example.hobbybungae.domain.userProfile.dto.UserProfileResponseDto;
import com.example.hobbybungae.domain.userProfile.exception.NotMatchingPasswordException;
import com.example.hobbybungae.domain.userProfile.dto.UserProfileUpdateRequestDto;
import com.example.hobbybungae.domain.userProfile.exception.NotMatchingIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 사용자 프로필 조회
    public UserProfileResponseDto getUser(Long userId , User inputUser) {
        if (!inputUser.getIdName().equals(userId)) {
            throw new NotMatchingIdException();
        }
        User user = getUserEntity(userId);
        return UserProfileResponseDto.of(user);
    }

    // Update Password
    @Transactional
    public UserProfileResponseDto updateUser(Long userId, UserProfileUpdateRequestDto requestDto, User inputUser) {
        if (!inputUser.getIdName().equals(userId)) {
            throw new NotMatchingIdException();
        }
        if (!requestDto.password().isEmpty()) {
            String inputPassword = requestDto.passwordReconfirm();
            if (!passwordEncoder.matches(inputPassword, inputUser.getPassword())) {
                throw new NotMatchingPasswordException();
            }
            // updatePassword(requestDto); // 지훈님 User에 만들어야함
        }
        // update(requestDto); // 지훈님 User에 만들어야함
        return UserProfileResponseDto.of(inputUser);
    }

    @Transactional(readOnly = true)
    public User getUserEntity(Long userId) {
        return userRepository.findById(userId).get();
                //.orElseThrow(() -> new UserNotFoundException("해당 프로필을 찾을 수 없습니다."));
    }

    // public void updatePassword(UserProfileUpdateRequestDto requestDto) {
    //     this.password = passwordEncoder.encode(requestDto.getPassword());
    // }

    // public void update(UserProfileUpdateRequestDto requestDto) {

    // }
}
