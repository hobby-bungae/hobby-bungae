package com.example.hobbybungae.domain.hobby.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "hobbies")
@NoArgsConstructor
@AllArgsConstructor
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hobbyId;

    @Column
    private String hobbyName;

    @Builder
    private Hobby(String hobbyName) {
        this.hobbyName = hobbyName;
    }
}
