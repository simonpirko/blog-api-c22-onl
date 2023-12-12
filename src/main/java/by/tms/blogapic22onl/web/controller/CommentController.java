package by.tms.blogapic22onl.web.controller;

import by.tms.blogapic22onl.entity.Comment;
import by.tms.blogapic22onl.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<Comment> addCommentToPost(@PathVariable Long postId, @PathVariable Long userId,
                                                    @RequestBody Comment comment) {
        Comment savedComment = commentService.addComment(postId, userId, comment);
        return ResponseEntity.ok(savedComment);
    }
}
