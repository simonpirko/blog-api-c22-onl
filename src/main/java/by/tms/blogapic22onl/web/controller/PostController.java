package by.tms.blogapic22onl.web.controller;

import by.tms.blogapic22onl.dto.PostDTO.CreatedPostDetails;
import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.entity.post.PostSort;
import by.tms.blogapic22onl.exception.UserNotFoundException;
import by.tms.blogapic22onl.mapper.GeneralMapper;
import by.tms.blogapic22onl.service.PostService;
import by.tms.blogapic22onl.service.UserService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/post")
public class PostController {

    private final PostService postService;
    private final GeneralMapper mapper;
    private final UserService userService;

    public PostController(PostService postService, GeneralMapper mapper, UserService userService) {
        this.postService = postService;
        this.mapper = mapper;
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@Validated @RequestBody CreatedPostDetails createdPostDetails,
                                           @RequestParam(name = "userId") Long userId,
                                           BindingResult bindingResult) throws UserNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new UserNotFoundException("User isn't found...");

        } else {
            Post post = postService.save(mapper.mapToPost(createdPostDetails));
            post.setUser(userService.findById(userId).get());

            return ResponseEntity.ok(postService.save(post));
        }
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Post> deletePost(@PathVariable("id") Long id) {
        Post post = postService.findById(id).orElseThrow();

        return ResponseEntity.ok(postService.removeById(post.getId()));

    }


    @PostMapping("/{id}/update")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id) {
        Post post = postService.findById(id).orElseThrow();

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
                                                                @RequestParam PostSort sortedBy) {

        User user = userService.findById(userId).orElseThrow();
        Slice<ViewedPostDetails> posts = postService.findAllByUser(user, PageRequest.of(offset, limit, sortedBy.getSortValue()));

        return ResponseEntity.ok(posts);
    }

}
