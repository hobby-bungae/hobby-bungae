package com.example.hobbybungae.domain;

import com.example.hobbybungae.userProfile.dto.UserProfileUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User extends TimeStamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_name",nullable = false)
    private String idName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column
    private String introduction;

    @OneToMany(mappedBy = "user")
    private List<UserHobby> userHobbyList = new ArrayList<>();

    public void update(UserProfileUpdateRequestDto requestDto) {
        this.idName = requestDto.getIdName();
        this.name = requestDto.getName();
        this.introduction = requestDto.getIntroduction();
        this.userHobbyList = requestDto.getHobbyList();
    }


    public void updatePassword(UserProfileUpdateRequestDto requestDto) {
        this.password = passwordEncoder.encode(requestDto.getPassword());
    }
}
