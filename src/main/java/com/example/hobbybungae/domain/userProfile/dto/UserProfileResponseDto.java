package com.example.hobbybungae.domain.userProfile.dto;

import com.example.hobbybungae.domain.user.entity.User;
import java.util.List;

public record UserProfileResponseDto<Hobby>(
        Long userId,
        String idName,
        String name,
        String introduction,
        List<Hobby> hobbyList
) {
    public static UserProfileResponseDto of(User user) {
        return new UserProfileResponseDto(
                user.getId(),
                user.getIdName(),
                user.getName(),
                user.getIntroduction(), // 지훈님 User에 만들어야함
                user.getHobbyList().stream()
                        .map(userHobby -> userHobby.getHobby()).toList()
        );
    }
}

