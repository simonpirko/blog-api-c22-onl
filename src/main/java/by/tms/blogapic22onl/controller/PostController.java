package by.tms.blogapic22onl.controller;

import by.tms.blogapic22onl.dto.PostDTO.CreatedPostDetails;
import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.exception.UserNotFoundException;
import by.tms.blogapic22onl.mapper.MapStructMapper;
import by.tms.blogapic22onl.service.PostService;
import by.tms.blogapic22onl.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/post")
public class PostController {

    private final PostService postService;
    private final MapStructMapper mapper;
    private final UserService userService;

    public PostController(PostService postService, MapStructMapper mapper, UserService userService) {
        this.postService = postService;
        this.mapper = mapper;
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@Validated @RequestBody CreatedPostDetails createdPostDetails,
                                           @RequestParam(name = "userId")Long userId, BindingResult bindingResult,
                                           Model model) throws UserNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new UserNotFoundException("User isn't found...");

        } else {
            Post post = postService.save(mapper.mapToPost(createdPostDetails));
            post.setUser(userService.findById(userId).get());

            return ResponseEntity.ok(postService.save(post));
        }
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Post> deletePost(){

        return ResponseEntity.ok(postService.remove(post));
    }



}
