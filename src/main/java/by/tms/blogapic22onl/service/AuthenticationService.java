package by.tms.blogapic22onl.service;

import by.tms.blogapic22onl.configuration.JWTTokenProvider;
import by.tms.blogapic22onl.configuration.SecurityConfiguration;
import by.tms.blogapic22onl.configuration.UserPrincipal;
import by.tms.blogapic22onl.dto.JwtAuthDTO.JWTAuthRequestDTO;
import by.tms.blogapic22onl.dto.JwtAuthDTO.JWTAuthResponseDTO;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.mapper.GeneralMapper;
import by.tms.blogapic22onl.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final GeneralMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;
//    private final Map<String, String> updateStorage = new HashMap<>();


    public JWTAuthResponseDTO authenticateAndGetToken(@NonNull JWTAuthRequestDTO jwtAuthRequestDTO)
            throws AuthException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mapper.mapToUser(jwtAuthRequestDTO).getUsername(),
                mapper.mapToUser(jwtAuthRequestDTO).getPassword()));

        UserPrincipal user = (UserPrincipal) userService.loadUserByUsername(mapper.mapToUser(jwtAuthRequestDTO).getUsername());
        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getPassword(), user.getRoles());

        if(authentication.isAuthenticated()){
            return  JWTAuthResponseDTO.builder().accessToken(token).build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }


}
