package com.example.hobbybungae.service;

import com.example.hobbybungae.domain.post.Dto.PostRequestDto;
import com.example.hobbybungae.domain.post.exception.PostNotFoundException;
import com.example.hobbybungae.domain.post.Dto.PostResponseDto;
import com.example.hobbybungae.domain.post.entity.PostEntity;
import com.example.hobbybungae.domain.post.repository.PostJpaRepository;
import com.example.hobbybungae.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;

    public PostResponseDto addPost(PostRequestDto requestDto) {
        // Dto -> Entity
        PostEntity postEntity = new PostEntity(requestDto);
        PostEntity savePost = postJpaRepository.save(postEntity);
        return new PostResponseDto(savePost);
    }

    public PostResponseDto getPost(Long postId) {
        PostEntity postEntity = getPostEntity(postId);
        return new PostResponseDto(postEntity);
    }

    public List<PostResponseDto> getPosts() {
        return postJpaRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, User user) {
        PostEntity postEntity = getPostEntity(postId);
//        verifyPassword(postEntity, requestDto.getPassword());
        postEntity.update(requestDto);
        return new PostResponseDto(postEntity);
    }

    public void deletePost(Long postId, User user) {
        PostEntity postEntity = getPostEntity(postId);
        postJpaRepository.delete(postEntity);
    }

    private PostEntity getPostEntity(Long postId) {
        return postJpaRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("해당 게시글을 찾을 수 없습니다."));
    }
}
