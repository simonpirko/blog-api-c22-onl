package by.tms.blogapic22onl.repository;


import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {

    Slice<ViewedPostDetails> findAllByUser(User user, Pageable pageable);

    Optional<Post> findByUserId(Long id);

//    @Query("SELECT p FROM Post p WHERE p.tagsList = : tagName")
@Query("SELECT p FROM Post p WHERE p.tagsList = : name")
List<Post> findPostByTagName(String name);


    @Query("SELECT p FROM Post p WHERE p IN (SELECT l.post FROM Like l WHERE l.user = :user)")
    List<Post> findPostsByLike(User user);


}
