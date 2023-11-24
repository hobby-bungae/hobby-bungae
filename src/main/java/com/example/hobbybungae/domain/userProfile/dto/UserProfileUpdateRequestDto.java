package com.example.hobbybungae.domain.userProfile.dto;

import java.util.List;

public record UserProfileUpdateRequestDto<Hobby> (
        String idName,
        String name,
        String passwordReconfirm,
        String password,
        String introduction,
        List<Hobby> hobbyList
) {

}