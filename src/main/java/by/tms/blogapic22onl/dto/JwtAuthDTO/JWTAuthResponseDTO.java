package by.tms.blogapic22onl.dto.JwtAuthDTO;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class JWTAuthResponseDTO {
    private final  String type = "Bearer";
    private String accessToken;
    private String updateToken;

}

