package by.tms.blogapic22onl.repository;

import by.tms.blogapic22onl.entity.Like;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.entity.post.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AutoGenerationRepository extends JpaRepository<Post, Long> {
}
