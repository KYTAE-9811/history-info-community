package com.kimyeontae.history_info.posts.controller;

import com.kimyeontae.history_info.comments.dto.CommentResponse;
import com.kimyeontae.history_info.comments.service.CommentService;
import com.kimyeontae.history_info.like.dto.LikeRequest;
import com.kimyeontae.history_info.like.service.LikeService;
import com.kimyeontae.history_info.posts.Posts;
import com.kimyeontae.history_info.posts.dto.PostRequest;
import com.kimyeontae.history_info.posts.dto.PostResponse;
import com.kimyeontae.history_info.posts.service.PostService;
import com.kimyeontae.history_info.topic.Topic;
import com.kimyeontae.history_info.topic.TopicService;
import com.kimyeontae.history_info.users.CustomUserDetail;
import com.kimyeontae.history_info.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/topic/{topicId}")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final TopicService topicService;
    private final LikeService likeService;
    private final UserService userService;


    @ModelAttribute("currentUser")
    public String addLoggedInUser(@AuthenticationPrincipal CustomUserDetail user) {
        return user != null ? user.getUsername() : null;
    }

    @ModelAttribute("topic")
    public Topic addTopic(@PathVariable("topicId") Long topicId) {
        return topicService.findTopic(topicId);
    }


    // 게시글 전체 목록 조회 (완)
    @GetMapping
    public String PostList(Model model, @PathVariable("topicId") Long topicId) {
        // 현재 로그인된 유저 보여줌
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail user = (CustomUserDetail) authentication.getPrincipal();
        System.out.println("Logged in user: " + user.getUsername());

        //특정 토픽에 속한 게시글만 조회하도록 함
        List<PostResponse> postResponses = postService.getAllPostByTopic(topicId);

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

        Long likeCount = likeService.getLikeCount(postId, "Post");
        model.addAttribute("likeCount", likeCount);
        // 게시글 좋아요 개수를 넘김

        List<CommentResponse> comments = commentService.getAllComments(postId);
        model.addAttribute("comments", comments);
        // 포스트 아이디로 가져온 해당 게시글의 댓글들을 모델로 넘김

        return "postDetail";
    }

    // 게시글 등록 페이지
    @GetMapping("/posts/new")
    public String PostWrite(Model model,
                            @PathVariable("topicId") Long topicId, @AuthenticationPrincipal CustomUserDetail user) {
        model.addAttribute("topicId", topicId);
        model.addAttribute("post", new PostRequest());
        model.addAttribute("writer", user.getUsername());

        return "postNew";
    }

    // 게시글 등록
    @PostMapping("/posts/new")
    public String PostWrite(Model model, @PathVariable("topicId") Long topicId, PostRequest postRequest, @AuthenticationPrincipal CustomUserDetail user){
        // 요청받은 게시글 정보 Post 엔티티에 저장
        Posts post = postRequest.toPosts();
        post.updateWriter(user.getUsername());
        Topic topic = topicService.findTopic(topicId);

        // 요청받은 주제 정보 Post 엔티티에 저장
        post.addTopic(topic);

        // 엔티티를 다시 postResponse DTO로 변환
        PostResponse postResponse = new PostResponse();
        postResponse.creatPostResponse(post);

        // 작성자 정보 Post 엔티티에 저장
        postService.addPost(post);

        model.addAttribute("topicId", topicId);
        model.addAttribute("post", postResponse);
        return String.format("redirect:/topic/%d", topicId);
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

    // 게시글 좋아요
    @PostMapping("/posts/{postId}/addlike")
    public String PostAddLike(Model model, @PathVariable("postId") Long postId) {

        model.addAttribute("postId", postId);
        String currentUsername = (String) model.getAttribute("currentUser");
        Long currentUserId = userService.findByUsername(currentUsername);
        Topic topic = (Topic) model.getAttribute("topic");

        LikeRequest likeRequest = new LikeRequest(currentUserId, postId, "POST");
        likeService.addLike(likeRequest);

        return "redirect:/topic/" + topic.getId() + "/posts/" + postId;
    }

    // 게시글 좋아요 취소
    @PostMapping("/{postId}/likes/remove")
    public String removeLikeFromPost(Model model ,@PathVariable Long postId) {

        model.addAttribute("postId", postId);
        String currentUsername = (String) model.getAttribute("currentUser");
        Long currentUserId = userService.findByUsername(currentUsername);

        Topic topic = (Topic) model.getAttribute("topic");

        likeService.removeLike(currentUserId, postId, "POST");

        return "redirect:/topic/"+ topic.getId() + "/posts/" + postId; // 게시글 상세 페이지로 리다이렉트
    }


    // 게시글 검색
    // 게시글 검색 후 페이징


}
