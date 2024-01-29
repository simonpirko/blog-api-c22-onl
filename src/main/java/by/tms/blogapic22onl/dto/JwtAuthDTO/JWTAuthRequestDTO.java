package by.tms.blogapic22onl.dto.JwtAuthDTO;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class JWTAuthRequestDTO {
    private Long id;
    private  String login;
    private  String password;
}
