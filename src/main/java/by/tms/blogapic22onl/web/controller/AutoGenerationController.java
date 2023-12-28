package by.tms.blogapic22onl.web.controller;

import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.service.AutoGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auto_generation")
public class AutoGenerationController {

    private final AutoGenerationService generationService;

    @GetMapping
    public ResponseEntity<List<Post>> generation (@RequestBody User user,
                                                  @RequestBody Post post) {
        return ResponseEntity.ok(generationService.autoGenerationPosts(post, user));
    }
}
