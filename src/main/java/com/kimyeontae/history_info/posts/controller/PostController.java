package com.kimyeontae.history_info.posts.controller;

import com.kimyeontae.history_info.comments.dto.CommentResponse;
import com.kimyeontae.history_info.comments.service.CommentService;
import com.kimyeontae.history_info.posts.Posts;
import com.kimyeontae.history_info.posts.dto.PostRequest;
import com.kimyeontae.history_info.posts.dto.PostResponse;
import com.kimyeontae.history_info.posts.service.PostService;
import com.kimyeontae.history_info.topic.Topic;
import com.kimyeontae.history_info.topic.TopicService;
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
@RequestMapping("/topic/{topicId}")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final TopicService topicService;

    // 게시글 전체 목록 조회 (완)
    @GetMapping
    public String PostList(Model model, @PathVariable("topicId") Long topicId) {

        List<PostResponse> postResponses = postService.getAllPost();
//        if (user != null) {
//            model.addAttribute("nickname", user.getNickname());
//        } else {
//            model.addAttribute("nickname", "게스트");
//        }

        Topic topic = topicService.findTopic(topicId);
        model.addAttribute("topicName", topic.getName());
        model.addAttribute("posts", postResponses);
        model.addAttribute("topicId", topicId);
        return "posts";
    }

    // 게시글 상세보기 (완)
    // 댓글 전체 보여주는 기능
    // 댓글 작성기능
    @GetMapping("/posts/{postId}")
    public String PostDetail(Model model,
                             @PathVariable("postId") Long postId,
                             @PathVariable("topicId") Long topicId) {

        model.addAttribute("topicId", topicId);

        PostResponse postResponse = postService.getPost(postId);
        model.addAttribute("post", postResponse);
        // 게시글 자체 내용을 넘김

        List<CommentResponse> comments = commentService.getAllComments(postId);
        model.addAttribute("comments", comments);
        // 포스트 아이디로 가져온 해당 게시글의 댓글들을 모델로 넘김

        return "post_detail";
    }

    // 게시글 등록 페이지
    @GetMapping("/posts/new")
    public String PostWrite(Model model,
                            @PathVariable("topicId") Long topicId) {
        model.addAttribute("topicId", topicId);
        model.addAttribute("post", new PostRequest());
        return "post_new";
    }

    // 게시글 등록
    @PostMapping("/posts/new")
    public String PostWrite(Model model, @PathVariable("topicId") Long topicId, PostRequest postRequest, @AuthenticationPrincipal CustomUserDetail user){
        // 요청받은 게시글 정보 Post 엔티티에 저장
        Posts post = postRequest.toPosts();
        Topic topic = topicService.findTopic(topicId);

        // 요청받은 주제 정보 Post 엔티티에 저장
        post.addTopic(topic);

        // 엔티티를 다시 postResponse DTO로 변환
        PostResponse postResponse = new PostResponse();
        postResponse.creatPostResponse(post);

        // 작성자 정보 Post 엔티티에 저장
        postResponse.updateWriter(user.getNickname());

        postService.addPost(post);

        model.addAttribute("topicId", topicId);
        model.addAttribute("post", postResponse);
        return "redirect:/posts/" + post.getId();
    }

    // 게시글 수정 페이지
    @GetMapping("/update")
    public String PostUpdate(Model model,@PathVariable("topicId") Long topicId) {
        model.addAttribute("topicId", topicId);
        return "post_update";
    }

    // 게시글 수정
    @PostMapping("/update")
    public String PostUpdate(Model model,@PathVariable("topicId") Long topicId, @PathVariable Long postId, PostRequest postRequest, String password) {

        model.addAttribute("topicId", topicId);
        Posts newPost = postRequest.toPosts();
        postService.updatePost(newPost, postId);

        return "redirect:/posts/" + postId;
    }

    // 게시글 검색
    // 게시글 검색 후 페이징
}
