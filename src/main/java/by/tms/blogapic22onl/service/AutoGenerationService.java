package by.tms.blogapic22onl.service;

import by.tms.blogapic22onl.entity.Tag;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.repository.AutoGenerationRepository;
import by.tms.blogapic22onl.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class AutoGenerationService {

    private final AutoGenerationRepository autoGeneration;
    private final PostRepository postRepository;

    public List<Post> getPostByUser(User user) {
        return postRepository.findPostsByLike(user);
    }

    public List<Tag> getAllTags(User user) {
        List<Tag> tags = new ArrayList<>();
        List<Post> allPostsByUserLike = getPostByUser(user);
        for (Post post : allPostsByUserLike) {
            tags.addAll(post.getTagsList());
        }
        return tags;
    }

    public Tag findRandomTag(User user) {
        List<Tag> tagList = getAllTags(user);
        Random random = new Random();
        int randomNumber = random.nextInt(tagList.size());
        return tagList.get(randomNumber);
    }

    public Optional<Post> findPost(User user) {
        return postRepository.findPostByTagName(findRandomTag(user).getName());
    }

    public Set<Long> autoGenerationPostId(User user) {
        int n = 0;

        Set<Long> postsId = new HashSet<>();
        while (n < 30) {
            Post post = findPost(user).orElseThrow();
            Optional<Post> byUserId = postRepository.findByUserId(user.getId());

            if (Objects.equals(byUserId.get().getId(), post.getId())) {
                break;
            }
            postsId.add(post.getId());
            n++;
        }

        return postsId;
    }

    public List<Post> autoGenerationPosts(User user) {
        List<Post> postList = new ArrayList<>();
        Set<Long> postId = autoGenerationPostId(user);

        for (Long l: postId) {
            postList.add(postRepository.findById(l).get());
        }
        return postList;
    }
}
