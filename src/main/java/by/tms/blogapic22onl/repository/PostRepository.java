package by.tms.blogapic22onl.repository;


import by.tms.blogapic22onl.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    Post save(Post post);
//
//    Optional<Post> findById(Long id);

    Page<Post> findAll(Pageable pageable);
//
//    List<Post> findAll();
//
//    void removeById(Long id);

}
