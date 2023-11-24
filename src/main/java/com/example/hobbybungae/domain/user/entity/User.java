package com.example.hobbybungae.domain.user.entity;

import com.example.hobbybungae.domain.comment.entity.Comment;
import com.example.hobbybungae.domain.common.TimeStamp;
import com.example.hobbybungae.domain.hobby.entity.Hobby;
import com.example.hobbybungae.domain.post.entity.Post;
import com.example.hobbybungae.domain.userProfile.dto.UserProfileUpdateRequestDto;
import com.example.hobbybungae.domain.userProfile.entity.UserHobby;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class User extends TimeStamp {
    //    @OneToMany(mappedBy = "user")
//    private List<UserHobby> userHobbyList = new ArrayList<>();
    @OneToMany(targetEntity = Post.class, mappedBy = "user")
    List<Post> posts;

    @OneToMany(targetEntity = Comment.class, mappedBy = "user")
    List<Comment> comments;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String idName;
    @Column(nullable = false)
    private String name;
    @Column
    private String Introduction;
    @Column(nullable = false)
    private String password;
    @OneToMany(targetEntity = UserHobby.class, mappedBy = "user")
    List<UserHobby> hobbyList;

    @Builder
    public User(Long id, String idName, String name, String introduction, String password) {
        this.id = id;
        this.idName = idName;
        this.name = name;
        this.Introduction = introduction;
        this.password = password;
    }
}
