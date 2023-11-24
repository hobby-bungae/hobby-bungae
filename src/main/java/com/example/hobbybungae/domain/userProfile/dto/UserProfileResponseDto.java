package com.example.hobbybungae.domain.userProfile.dto;

import com.example.hobbybungae.domain.user.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public record UserProfileResponseDto<Hobby>(
        Long userId,
        Long idName,
        String name,
        String introduction
        State state,
        List<Hobby> hobbyList
) {
    public UserProfileResponseDto(User user) {
        return new UserProfileResponseDto(
                user.getId(),
                user.getIdName(),
                user.getName(),
                user.getState(),
                user.getIntroduction(),
                user.getUserHobbyList()
        );
    }
}

