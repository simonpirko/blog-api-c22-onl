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

//    @Query
//    Page<Post> findAllWithPageable(User user, Pageable pageable);

    void deleteById(Long id);

    Optional<Post> findByUserId(Long id);
}