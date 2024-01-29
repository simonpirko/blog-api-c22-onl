package by.tms.blogapic22onl.web.controller;

import by.tms.blogapic22onl.dto.PostDTO.CreatedPostDetails;
import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import by.tms.blogapic22onl.entity.Comment;
import by.tms.blogapic22onl.entity.Tag;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.entity.post.PostSort;
import by.tms.blogapic22onl.mapper.GeneralMapper;
import by.tms.blogapic22onl.service.CommentService;
import by.tms.blogapic22onl.service.PostService;
import by.tms.blogapic22onl.service.TagService;
import by.tms.blogapic22onl.service.UserService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final GeneralMapper mapper;
    private final UserService userService;
    private final TagService tagService;
    private final CommentService commentService;


    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@Validated @RequestBody CreatedPostDetails createdPostDetails,
                                           @RequestParam(name = "userId") Long userId,
                                           @RequestParam(name = "tag_name") String tagName,
                                           @RequestBody List<Tag> tagsList){

            Post post = postService.save(mapper.mapToPost(createdPostDetails));
            post.setUser(userService.findById(userId).get());

            Tag tag = tagService.findByName(tagName).orElseThrow();
            if(!tag.getName().equals(tagName)){
                tag = (tagService.save(tag));
                tagsList.add(tag);
            }
            post.setTagsList(tagsList);

            return ResponseEntity.ok(postService.save(post));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") Long id) {
        Post post = postService.findById(id).orElseThrow();
        if (!post.getTagsList().isEmpty()){
          post.getTagsList().clear();
        }

        return ResponseEntity.ok(postService.removeById(post.getId()));

    }


    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody List<Tag> tags) {
        Post post = postService.findById(id).orElseThrow();

        if (tags.isEmpty()) {
            post.setTagsList(tags);
        }
        return ResponseEntity.ok(postService.update(post));
    }


    @GetMapping("/{id}/get")
    public ResponseEntity<Post> getPost(@PathVariable("id") Long id) {
        Post post = postService.findById(id).orElseThrow();

        return ResponseEntity.ok(post);

    }


    @GetMapping("/get_all/{page}")
    public ResponseEntity<Slice<ViewedPostDetails>> getAllPosts(@Validated @RequestBody ViewedPostDetails viewedPostDetails,
                                                                @RequestParam("userId") Long userId,
                                                                @PathVariable(value = "page") Optional<Integer> page,
                                                                @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                                @RequestParam(value = "limit", defaultValue = "10") @Min(5) @Max(30) Integer limit,
                                                                @RequestParam PostSort sortedBy,
                                                                @RequestParam(value = "comment_offset", defaultValue = "0") @Min(0) Integer comment_offset,
                                                                @RequestParam(value = "comment_limit", defaultValue = "3") Integer comment_limit) {

        User user = userService.findById(userId).orElseThrow();
        Slice<ViewedPostDetails> posts = postService.findAllByUser(user, PageRequest.of(offset, limit, sortedBy.getSortValue()));

        Slice<Comment> comments = commentService.findAllByPost(posts.get().findAny(), PageRequest.of(offset, limit));

        return ResponseEntity.ok(posts);
//        return new ResponseEntity<>(posts, comments, HttpStatus.OK);
    }

}
