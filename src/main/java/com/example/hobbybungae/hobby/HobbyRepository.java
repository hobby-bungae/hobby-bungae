package com.example.hobbybungae.hobby;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {

    Optional<Hobby> findByHobbyName(String hobbyName);
}