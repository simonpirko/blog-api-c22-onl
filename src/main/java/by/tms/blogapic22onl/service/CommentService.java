package by.tms.blogapic22onl.service;

import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import by.tms.blogapic22onl.entity.Comment;
import by.tms.blogapic22onl.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService{

    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        comment.setCreatedDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public Optional<Comment> findById(Long aLong) {
        return Optional.ofNullable(commentRepository.findById(aLong).orElseThrow(RuntimeException::new));
    }


    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Slice<Comment> findAllByPost(Optional<ViewedPostDetails> post, Pageable pageable){
        return commentRepository.findAllByPost(post, pageable);
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
