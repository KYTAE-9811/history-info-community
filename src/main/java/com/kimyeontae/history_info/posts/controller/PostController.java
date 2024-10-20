package com.kimyeontae.history_info.posts.controller;

import com.kimyeontae.history_info.Topic;
import com.kimyeontae.history_info.comments.dto.CommentResponse;
import com.kimyeontae.history_info.comments.service.CommentService;
import com.kimyeontae.history_info.posts.Posts;
import com.kimyeontae.history_info.posts.dto.PostRequest;
import com.kimyeontae.history_info.posts.dto.PostResponse;
import com.kimyeontae.history_info.posts.service.PostService;
import com.kimyeontae.history_info.users.CustomUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Writer;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/{topic}")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    // 게시글 전체 목록 조회 (완)
    @GetMapping
    public String PostList(Model model, @PathVariable Topic topic) {
        List<PostResponse> postResponses = postService.getAllPost();
        model.addAttribute("topic", topic);
        model.addAttribute("posts", postResponses);
        return "posts";
    }

    // 게시글 상세보기 (완)
    // 댓글 전체 보여주는 기능
    // 댓글 작성기능
    @GetMapping("/posts/{postId}")
    public String PostDetail(Model model,
                             @PathVariable("topic") Topic topic,
                             @PathVariable("postId") Long postId) {

        PostResponse postResponse = postService.getPost(postId);
        model.addAttribute("topic", topic);
        model.addAttribute("post", postResponse);
        // 게시글 자체 내용을 넘김

        List<CommentResponse> comments = commentService.getAllComments(postId);
        model.addAttribute("comments", comments);
        // 포스트 아이디로 가져온 해당 게시글의 댓글들을 모델로 넘김

        return "post_detail";
    }

    // 게시글 등록 페이지
    @GetMapping("/posts/write")
    public String PostWrite(Model model, @PathVariable Topic topic) {
        model.addAttribute("topic", topic);
        return "post_write";
    }

    // 게시글 등록
    @PostMapping("/posts/write")
    public String PostWrite(Model model, @PathVariable Topic topic, PostRequest postRequest, @AuthenticationPrincipal CustomUserDetail user, Writer writer){
        // 요청받은 게시글 정보 Post 엔티티에 저장
        Posts post = postRequest.toPosts();

        // 요청받은 주제 정보 Post 엔티티에 저장
        post.addTopic(topic);

        // 작성자 정보 Post 엔티티에 저장
        post.updateWriter(user.getNickname());

        postService.addPost(post);

        model.addAttribute("post", post);
        return "redirect:/posts/" + post.getId();
    }

    // 게시글 수정 페이지
    @GetMapping("/posts/{postId}/update")
    public String PostUpdate(Model model, @PathVariable Topic topic) {
        model.addAttribute("topic", topic);
        return "post_update";
    }

    // 게시글 수정
    @PostMapping("/posts/{postId}/update")
    public String PostUpdate(Model model, @PathVariable Topic topic, @PathVariable Long postId, PostRequest postRequest) {
        Posts newPost = postRequest.toPosts();
        postService.updatePost(newPost, postId);
        return "redirect:/posts/" + postId;
    }

    // 게시글 검색
    // 게시글 검색 후 페이징
}
