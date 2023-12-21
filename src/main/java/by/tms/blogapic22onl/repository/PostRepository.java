package by.tms.blogapic22onl.repository;


import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {

    Slice<ViewedPostDetails> findAllByUser(User user, Pageable pageable);

    Optional<Post> findByUserId(Long id);
}
