package com.example.hobbybungae.domain.user.dto.response;


import com.example.hobbybungae.domain.user.entity.User;

public record UserResponseDto(
        String idName,
        String name) {
    public static SuccessResponseDto successResponseOf(User newUser) {
        UserResponseDto userResponseDto = new UserResponseDto(newUser.getIdName(), newUser.getName());
        return new SuccessResponseDto(userResponseDto);
    }
}
