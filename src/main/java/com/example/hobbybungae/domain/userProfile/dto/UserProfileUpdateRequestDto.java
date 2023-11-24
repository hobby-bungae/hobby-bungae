package com.example.hobbybungae.domain.userProfile.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UserProfileUpdateRequestDto {
    private String idName;
    private String name;
    private String passwordReconfirm;
    private String password;
    private String introduction;
    private State state;
    private List<Hobby> hobbyList;
}
