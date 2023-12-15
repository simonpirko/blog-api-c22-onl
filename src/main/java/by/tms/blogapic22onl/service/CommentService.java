package by.tms.blogapic22onl.service;

import by.tms.blogapic22onl.entity.Comment;
import by.tms.blogapic22onl.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class CommentService{

    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        comment.setCreatedDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public Optional<Comment> findById(Long aLong) {
        return Optional.ofNullable(commentRepository.findById(aLong).orElseThrow(RuntimeException::new));
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public void remove(Comment comment) {
        commentRepository.delete(comment);
    }

    public void removeById(Long id) {
        Optional<Comment> comment = Optional.ofNullable(commentRepository.findById(id).
                orElseThrow(RuntimeException::new));
        if (comment.isPresent()) {
            commentRepository.deleteById(id);
        }
    }

    public void update(Comment comment) {
        commentRepository.save(comment);
    }
}
