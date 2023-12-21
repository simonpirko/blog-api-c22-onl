package by.tms.blogapic22onl.web.controller;

import by.tms.blogapic22onl.entity.Comment;
import by.tms.blogapic22onl.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody Comment comment) {
        log.info("Creating comment: {}", comment);
        Comment savedComment = commentService.save(comment);

        log.info("Comment created: {}", savedComment);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id) {
        log.info("Search comment by ID: {}", id);
        Optional<Comment> comment = commentService.findById(id);

        if (comment.isPresent()){
            log.info("Comment found: {}", comment.get());
            return ResponseEntity.ok(comment.get());
        } else {
            log.info("Comment not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        log.info("Receiving all comments");
        List<Comment> comments = commentService.findAll();
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable("id") Long id) {
        log.info("Deleting comment by ID: {}", id);
        commentService.removeById(id);

        log.info("Comment deleted with ID: {}", id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateCommentById(@PathVariable("id") Long id, @RequestBody Comment comment) {

        log.info("Updating comment with ID: {}", id);
        Optional<Comment> commentById = commentService.findById(id);
        if (commentById.isPresent()){
            comment.setId(id);
            Comment updatedComment = commentService.save(comment);
            log.info("Comment updated: {}", updatedComment);
            return ResponseEntity.ok(updatedComment);
        }
        log.info("Comment not found with ID: {}", id);
        return ResponseEntity.notFound().build();
    }

}
