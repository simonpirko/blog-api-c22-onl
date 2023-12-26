package by.tms.blogapic22onl.service;

import by.tms.blogapic22onl.configuration.jwt.JwtTokenProvider;
import by.tms.blogapic22onl.entity.Role;
import by.tms.blogapic22onl.entity.User;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final Map<String, String> updateStorage = new HashMap<>();
    private final JwtTokenProvider jwtTokenProvider;
    @Setter
    @Getter
    public  class  JwtRequest{
        private Long id;
        private  String login;
        private  String password;
    }


    @Getter
    @AllArgsConstructor
    public class  JwtResponse{
        private final  String type = "Bearer";
        private String accessToken;
        private String updateToken;

    }

    public JwtResponse login(@NonNull JwtRequest authRequest)
            throws AuthException {
        final User user = userService.findById(authRequest.getId())
                .orElseThrow(() -> new AuthException("User is not found"));
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtTokenProvider.generateAccessToken(user);
            final String updateToken = jwtTokenProvider.generateUpdateToken(user);
            updateStorage.put(user.getPassword(), updateToken);
            return new JwtResponse(accessToken, updateToken);
        } else {
            throw new AuthException("Wrong password");
        }
    }

    public JwtResponse getAccessToken(@NonNull String updateToken) {
        if (jwtTokenProvider.validateToken(updateToken)) {
            final Claims claims = jwtTokenProvider.getUpdateClaims(updateToken);
            final String login = claims.getSubject();
            final String saveUpdateToken = updateStorage.get(login);
            if (saveUpdateToken != null && saveUpdateToken.equals(updateToken)) {
                final User user = userService.findById(id)
                        .orElseThrow(() -> new AuthException("User is not found"));
                final String accessToken = jwtTokenProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse update(@NonNull String updateToken) throws AuthException {
        if (jwtTokenProvider.validateToken(updateToken)) {
            final Claims claims = jwtTokenProvider.getUpdateClaims(updateToken);
            final String login = claims.getSubject();
            final String saveUpdateToken = updateStorage.get(login);
            if (saveUpdateToken != null && saveUpdateToken.equals(updateToken)) {
                final User user = userService.findById(id)
                        .orElseThrow(() -> new AuthException("User is not found"));
                final String accessToken = jwtTokenProvider.generateAccessToken(user);
                final String newUpdateToken = jwtTokenProvider.generateUpdateToken(user);
                updateStorage.put(user.getPassword(), newUpdateToken);
                return new JwtResponse(accessToken, newUpdateToken);
            }
        }
        throw new AuthException("Invalid JWT token");
    }
    public Authentication getAuthInfo(){
        return (Authentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
