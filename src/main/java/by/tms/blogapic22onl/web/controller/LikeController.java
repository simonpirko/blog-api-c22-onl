package by.tms.blogapic22onl.web.controller;

import by.tms.blogapic22onl.entity.Like;
import by.tms.blogapic22onl.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;


    @PostMapping
    public ResponseEntity<Like> create(@RequestBody Like like) {
        return ResponseEntity.ok(likeService.save(like));
    }

    @GetMapping
    public ResponseEntity<List<Like>> getAllLikes() {
        return ResponseEntity.ok(likeService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLikeById(@PathVariable("id") Long id) {
        likeService.removeById(id);
        return ResponseEntity.ok().build();
    }
}
