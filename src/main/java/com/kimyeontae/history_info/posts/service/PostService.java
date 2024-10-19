package com.kimyeontae.history_info.posts.service;

import com.kimyeontae.history_info.posts.Posts;
import com.kimyeontae.history_info.posts.repository.PostRepository;
import com.kimyeontae.history_info.posts.dto.PostResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// 컨트롤러에서 받은 요청 dto를 반환해서 서비스로 엔티티를 넘기고,
// 리포지토리에서 받은 엔티티를 응답 dto로 반환해서 컨트롤러로 넘긴다.

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /*
    1. 게시글 등록
    */
    public Long addPost(Posts posts) {
        postRepository.save(posts);
        return posts.getId();
    }
    /*
    2. 게시글 수정
     */
    public Long updatePost(Posts newPosts, Long postId) {
        Posts post = postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("해당 포스트가 없습니다."));
        post.updatePosts(newPosts.getTitle(), newPosts.getContent());
        return postId;
    }

    /*
    3. 게시글 삭제
     */
    public Long deletePost(Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }

    /*
    4. 게시글 전체 조회
     */
    public List<PostResponse> getAllPost() {
        List<Posts> posts = postRepository.findAll();
        List<PostResponse> PostResponses = new ArrayList<>();
        for (Posts post : posts) {
            PostResponses.add(new PostResponse().creatPostResponse(post));
        }
        return PostResponses;
    }

    /*
    5.게시글 단건 조회 (댓글도 같이 조회되어야 함)
     */
    public PostResponse getPost(Long postId) {
        PostResponse postResponse = new PostResponse();
        Posts post = postRepository.findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("해당 포스트가 없습니다."));
        return postResponse.creatPostResponse(post);
    }


    /*
    6.좋아요, 싫어요, 조회수
     */

    public void addPostLike(Posts posts) {
        postRepository.findById(posts.getId());
        posts.addLikes();
    }

    public void addPostDisLike(Posts posts) {
        postRepository.findById(posts.getId());
        posts.addDislikes();
    }

    public void addViews(Posts posts) {
        postRepository.findById(posts.getId());
        posts.addViews();
    }
}
