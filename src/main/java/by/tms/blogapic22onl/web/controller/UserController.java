package by.tms.blogapic22onl.web.controller;

import by.tms.blogapic22onl.configuration.JWTTokenProvider;
import by.tms.blogapic22onl.configuration.UserPrincipal;
import by.tms.blogapic22onl.dto.UserDTO.LoginUserDto;
import by.tms.blogapic22onl.entity.Role;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Simon Pirko on 7.12.23
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public ResponseEntity<User> registration (@RequestBody User user){
            return ResponseEntity.ok().body(userService.save(user));
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDto userDto){
        UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername(userDto.getUsername());

        if (passwordEncoder.matches(userDto.getPassword(), userPrincipal.getPassword())) {
            Set<Role> authorities = (Set<Role>) userPrincipal.getAuthorities();
            String token = jwtTokenProvider.generateToken(userPrincipal.getUsername(), userPrincipal.getPassword(), authorities);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }
}
