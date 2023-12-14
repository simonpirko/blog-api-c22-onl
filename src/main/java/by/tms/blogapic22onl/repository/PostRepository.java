package by.tms.blogapic22onl.repository;


import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    Page<Post> findAll(Pageable pageable);

    Page<Post> findAll(Pageable pageable);
    Slice<ViewedPostDetails> findAllByUser(User user, Pageable pageable);

    Optional<Post> findByUserId(Long id);
}
