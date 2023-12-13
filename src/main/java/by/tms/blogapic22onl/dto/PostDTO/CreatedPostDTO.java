package by.tms.blogapic22onl.dto.PostDTO;

import by.tms.blogapic22onl.entity.post.PostSource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CreatedPostDTO {

    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    private String description;

    @NotBlank
    @NotNull
    private Set<PostSource> contentType;

}
