package by.tms.blogapic22onl.repository;


import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    Page<Post> findAll(User user, Pageable pageable);
    Page<Post> findAll(Pageable pageable);

    Optional<Post> findByUserId(Long id);
}
