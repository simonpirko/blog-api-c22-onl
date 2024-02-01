package by.tms.blogapic22onl.repository;

import by.tms.blogapic22onl.entity.Tag;
import by.tms.blogapic22onl.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag>findByName(String name);

//    @Query("SELECT t FROM Tag t JOIN Post p WHERE p.id = :post_id")
    @Query("SELECT t FROM Tag t WHERE Post.id = : id")
    List<Tag>findAllByPost(Post post);

}
