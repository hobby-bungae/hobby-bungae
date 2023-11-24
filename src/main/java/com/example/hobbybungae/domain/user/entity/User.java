package com.example.hobbybungae.domain.user.entity;

import com.example.hobbybungae.domain.userProfile.entity.UserHobby;
import com.example.hobbybungae.domain.common.TimeStamp;
import com.example.hobbybungae.domain.userProfile.dto.UserProfileUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class User extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
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

