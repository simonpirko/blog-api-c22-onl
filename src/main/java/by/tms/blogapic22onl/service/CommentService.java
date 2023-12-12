package by.tms.blogapic22onl.service;

import by.tms.blogapic22onl.entity.Comment;
import by.tms.blogapic22onl.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Comment addComment(Long postId, Long userId, Comment comment) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalPost.isPresent() && optionalUser.isPresent()) {
            Post post = optionalPost.get();
            User user = optionalUser.get();

            comment.setPost(post);
            comment.setUser(user);
            comment.setDate(LocalDateTime.now());

            return commentRepository.save(comment);
        } else {
            throw new IllegalArgumentException("Invalid post or user ID");
        }
    }
}
