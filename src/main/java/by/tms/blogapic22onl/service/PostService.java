package by.tms.blogapic22onl.service;

import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import by.tms.blogapic22onl.entity.Tag;
import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.repository.PostRepository;
import by.tms.blogapic22onl.repository.TagRepository;
import by.tms.blogapic22onl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;


    public Post save(Post post) {
        post.setCreationDate(LocalDateTime.now());

        return postRepository.save(post);
    }


    @Transactional(readOnly = true)
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(postRepository.findById(id).orElseThrow());
    }


    @Transactional(readOnly = true)
    public Page<Post> findAll(Pageable pageable) {
        return (Page<Post>) postRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Slice<ViewedPostDetails> findAllByUser(User user, Pageable pageable){
        return postRepository.findAllByUser(user, pageable);
    }

    public void remove(Post post) {
        postRepository.delete(post);

    }


//    public void remove(Post post, List<Tag> tags) {
//
//        postRepository.delete(post);
//        if (tags.size() != 0){
//            tags.clear();
//        }
//
//    }


    public Post update(Post post) {
        return postRepository.save(post);
    }


    public Post removeById(Long id) {
        Optional<Post> postById = Optional.of(postRepository.findById(id).orElseThrow());

        if (postById.isPresent()) {
            postRepository.deleteById(id);
        }
        return postById.get();
    }



    @Transactional(readOnly = true)
   public Optional<Post> findByUserId(Long id){
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
