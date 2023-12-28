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

    public List<Post> autoGenerationPosts(Post post, User user) {
        int n = 0;
        List<Post> postByTag = postRepository.findPostByTagName(findRandomTag(user).getName());
        List<Post> postList = new ArrayList<>();

        Post p = postList.get(new Random().nextInt(postByTag.size()));
        while (n < 30) {

            if (!p.getId().equals(post.getUser().getId()) || !postList.contains(p)) {

                postList.add(postList.get(new Random().nextInt(postByTag.size())));
                postByTag = postRepository.findPostByTagName(findRandomTag(user).getName());
                p = postList.get(new Random().nextInt(postByTag.size()));
                n++;
            }
        }
        return postList;
    }
}
