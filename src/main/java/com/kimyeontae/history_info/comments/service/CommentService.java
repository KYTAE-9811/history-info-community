package com.kimyeontae.history_info.comments.service;

import com.kimyeontae.history_info.comments.Comment;
import com.kimyeontae.history_info.comments.repository.CommentRepository;
import com.kimyeontae.history_info.comments.dto.CommentResponse;
import com.kimyeontae.history_info.posts.Posts;
import com.kimyeontae.history_info.posts.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    /*
    1. 해당 포스트에 댓글 등록
    */
    public void addComment(Long postId, Comment comment) {
        Posts post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
        post.addComment(comment);
        comment.setPost(post);
        commentRepository.save(comment);
    }
    /*
    2. 댓글 수정
     */
    public Long updateComment(Comment newComment, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new EntityNotFoundException("해당 댓글이 없습니다."));
        // 영속성 컨텍스트에서 comment 관리, 더티체킹을 활용한 수정
        comment.updateComment(newComment.getContent());
        return commentId;
    }

    /*
    3. 댓글 삭제
     */
    public Long deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        // 댓글이 위치했던 게시글의 댓글 개수를 하나 줄임
        comment.getPost().minusComments_count();
        commentRepository.deleteById(commentId);
        return commentId;
    }

    /*
    4. 해당 포스트 댓글 전체 조회
     */
    public List<CommentResponse> getAllComments(Long postId) {
        Posts post = postRepository.findById(postId).orElseThrow((() -> new EntityNotFoundException("없습니다")));
        List<Comment> comments = post.getComments();
        List<CommentResponse> CommentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponses.add(new CommentResponse().createCommentResponse(comment));
        }
        return CommentResponses;
    }

    /*
    5.게시글 단건 조회 (댓글도 같이 조회되어야 함)
     */
    public CommentResponse getComment(Long commentId) {
        CommentResponse commentResponse = new CommentResponse();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new EntityNotFoundException("해당 포스트가 없습니다."));
        return commentResponse.createCommentResponse(comment);
    }

    /*
    6.좋아요, 싫어요, 조회수
     */

    public void addCommentLike(Posts posts) {
        commentRepository.findById(posts.getId());
        posts.addLikes();
    }

    public void addCommentDisLike(Posts posts) {
        commentRepository.findById(posts.getId());
        posts.addDislikes();
    }

}
