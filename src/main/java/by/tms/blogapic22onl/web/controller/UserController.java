package by.tms.blogapic22onl.web.controller;

import by.tms.blogapic22onl.configuration.UserPrincipal;
import by.tms.blogapic22onl.configuration.jwt.JwtTokenProvider;
import by.tms.blogapic22onl.dto.UserDTO.LoginUserDto;
import by.tms.blogapic22onl.entity.Role;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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
    private final JwtTokenProvider jwtTokenProvider;


    @GetMapping("/registration")
    public String registration() {
        return "reg";
    }

    @PostMapping("/registration")
    public String registration(User user) {
        userService.save(user);
        return "redirect:/";
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDto loginUserDto) {
        UserPrincipal userDetails = (UserPrincipal) userService.loadUserByUsername(loginUserDto.getUsername());

        if (passwordEncoder.matches(loginUserDto.getPassword(), userDetails.getPassword())) {
            Set<Role> authorities = (Set<Role>) userDetails.getAuthorities();
            String token = jwtTokenProvider.generateToken(userDetails.getId(), userDetails.getUsername(), userDetails.getPassword(), authorities);

            return ResponseEntity.ok(token);
        }

        return ResponseEntity.badRequest().build();
    }



    public ResponseEntity<Void> create() {
        log.info("Test");
        return ResponseEntity.ok().build();
    }
}
