package by.tms.blogapic22onl.repository;

import by.tms.blogapic22onl.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
