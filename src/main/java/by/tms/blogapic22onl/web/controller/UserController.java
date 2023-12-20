package by.tms.blogapic22onl.web.controller;

import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Simon Pirko on 7.12.23
 */

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/registration")
    public String registration() {
        return "reg";
    }
    @PostMapping("/registration")
    public String registration (User user){
            userService.save(user);
            return "redirect:/";
    }

    public ResponseEntity<Void> create() {
        log.info("Test");
        return ResponseEntity.ok().build();
    }
}
