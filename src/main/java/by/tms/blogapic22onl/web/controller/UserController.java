package by.tms.blogapic22onl.web.controller;

import by.tms.blogapic22onl.configuration.JWTTokenProvider;
import by.tms.blogapic22onl.configuration.UserPrincipal;
import by.tms.blogapic22onl.dto.EmailDTO.SimpleEmailDetails;
import by.tms.blogapic22onl.dto.JwtAuthDTO.JWTAuthRequestDTO;
import by.tms.blogapic22onl.dto.JwtAuthDTO.JWTAuthResponseDTO;
import by.tms.blogapic22onl.dto.UserDTO.LoginUserDto;
import by.tms.blogapic22onl.entity.Role;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.mapper.GeneralMapper;
import by.tms.blogapic22onl.service.AuthenticationService;
import by.tms.blogapic22onl.service.UserService;
import by.tms.blogapic22onl.service.emailService.EmailService;
import jakarta.mail.MessagingException;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Simon Pirko on 7.12.23
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final GeneralMapper mapper;
    private final AuthenticationService authenticationService;



    @PostMapping("/generateToken")
    public ResponseEntity<JWTAuthResponseDTO> authenticateAndGetToken(@io.swagger.v3.oas.annotations.parameters.RequestBody JWTAuthRequestDTO jwtAuthRequestDTO) {
        JWTAuthResponseDTO token;
        try {
            token = authenticationService.authenticateAndGetToken(jwtAuthRequestDTO);
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(token);
    }


    @PostMapping("/registration")
    public ResponseEntity<User> registration (@RequestBody User user, @RequestBody SimpleEmailDetails simpleEmailDetails){
        userService.save(user);
        try {
            emailService.sendSimpleEmail(simpleEmailDetails);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(user);

    }


    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDto userDto){
        UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername(userDto.getUsername());

        if (passwordEncoder.matches(userDto.getPassword(), userPrincipal.getPassword())) {
            Set<Role> authorities = (Set<Role>) userPrincipal.getAuthorities();
            LocalDateTime time = LocalDateTime.now();
            mapper.mapToUser(userDto).setLastVisitDate(time);
            String token = jwtTokenProvider.generateToken(userPrincipal.getUsername(), userPrincipal.getPassword(), authorities);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }
}
