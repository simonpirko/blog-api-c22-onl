package by.tms.blogapic22onl.controller;

import by.tms.blogapic22onl.dto.PostDTO.CreatedPostDetails;
import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.exception.UserNotFoundException;
import by.tms.blogapic22onl.mapper.GeneralMapper;
import by.tms.blogapic22onl.service.PostService;
import by.tms.blogapic22onl.service.UserService;
import org.springframework.data.domain.Pageable;
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
                                           @RequestParam(name = "userId")Long userId,
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
    public ResponseEntity<Post> deletePost(@PathVariable("id") Long id){
        Post post = postService.findById(id).orElseThrow();

            return ResponseEntity.ok(postService.removeById(post.getId()));

    }


    @PostMapping("/{id}/update")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id){
        Post post = postService.findById(id).orElseThrow();

        return ResponseEntity.ok(postService.update(post));
    }


    @GetMapping("/{id}/get")
    public ResponseEntity<Post> getPost(@PathVariable("id") Long id) {
        Post post = postService.findById(id).orElseThrow();

        return ResponseEntity.ok(post);

    }


    @GetMapping("/get_all")
    public ResponseEntity<List<Post>> getAllPosts(@Validated @RequestBody ViewedPostDetails viewedPostDetails,
                                                  @RequestParam("userId") Long userId,
                                                  @RequestParam Optional<Integer> page,
                                                  @RequestParam Optional<Integer> size,
                                                  @RequestParam Optional<String> sortedBy){
        List<Post> posts = (List<Post>) postService.findAll(Pageable.unpaged());
return ResponseEntity.ok(posts);
    }



}
