package by.tms.blogapic22onl.service;

import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.repository.PostRepository;
import by.tms.blogapic22onl.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
//@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post save(Post post) {
        post.setCreationDate(LocalDateTime.now());

        return postRepository.save(post);
    }

    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with " + id + " isn't found...")));
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void remove(Post post) {
        postRepository.delete(post);

    }

    public void update(Post post) {
postRepository.save(post);
    }


    public void removeById(Long id) {
        Optional<Post> postById = Optional.ofNullable(postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with " + id + " isn't found...")));

        if(postById.isPresent()) {
            postRepository.deleteById(id);
        }

    }

    Page<Post> findAll(User user, Pageable pageable){
return null;
    }

    Page<Post> findAll(Pageable pageable){
        return null;
    }

    Optional<Post> findByUserId(Long id){
        Optional<User> userById = userRepository.findById(id);

        if(userById.isPresent()) {
            Optional<Post> postByUserId = postRepository.findByUserId(userById.get().getId());

            return postByUserId;
        }
        else{
            return Optional.empty();
        }
    }
}
