package com.kimyeontae.history_info.posts.service;

import com.kimyeontae.history_info.posts.Posts;
import com.kimyeontae.history_info.posts.controller.PostRepository;
import com.kimyeontae.history_info.posts.dto.PostResponse;
import jakarta.annotation.security.RunAs;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    @WithMockUser("test@naver.com")
    void addPost() {
        //given
        Posts posts = new Posts("아아", "으아아ㅏ아아","/image/good", "1");

        //when
        Long postId = postService.addPost(posts);

        //then
        Posts result = postRepository.findById(postId).orElse(null);
        Assertions.assertThat(result).isEqualTo(posts);
        System.out.println("result = " + result);
    }

    @Test
    void updatePost() {
        //given
        Posts posts = new Posts("아아", "으아아ㅏ아아","/image/good", "1");
        Long postId = postService.addPost(posts);
        Posts newPosts = new Posts("훗", "후후훗","/image/new", "2");
        //when
        Long result = postService.updatePost(newPosts, postId);

        //then
        Posts vali = postRepository.findById(postId).orElse(null);

        Assertions.assertThat(postId).isEqualTo(vali.getId());
        System.out.println("vali.getContent() = " + vali.getContent());
        System.out.println("vali.getTitle = " + vali.getTitle());
        System.out.println("vali.getUpdatedAt() = " + vali.getUpdatedAt());
        System.out.println("vali.getCreatedAt() = " + vali.getCreatedAt());
    }

    @Test
    void deletePost() {
        //given
        Posts posts = new Posts("아아", "으아아ㅏ아아","/image/good", "1");
        Long postId = postService.addPost(posts);

        //when
        postService.deletePost(postId);

        //then
        List<Posts> results = postRepository.findAll();
        Assertions.assertThat(results).hasSize(0);

    }

    @Test
    void getAllPost() {
        //given
        Posts posts = new Posts("아아", "으아아ㅏ아아","/image/good", "1");
        Long postId = postService.addPost(posts);
        Posts newPosts = new Posts("훗", "후후훗","/image/new", "2");
        Long postId2 = postService.addPost(newPosts);

        //when
        List<PostResponse> results = postService.getAllPost();

        //then
        Assertions.assertThat(results).hasSize(2);
    }

    @Test
    void getPost() {
        //given
        Posts posts = new Posts("아아", "으아아ㅏ아아","/image/good", "1");
        Long postId = postService.addPost(posts);
        Posts newPosts = new Posts("훗", "후후훗","/image/new", "2");
        Long postId2 = postService.addPost(newPosts);
    }
}