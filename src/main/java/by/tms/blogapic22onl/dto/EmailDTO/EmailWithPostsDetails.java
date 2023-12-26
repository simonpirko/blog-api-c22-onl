package by.tms.blogapic22onl.dto.EmailDTO;

import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.entity.post.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class EmailWithPostsDetails {

    @NotBlank
    @NotNull
    private User recipient;

//    @NotBlank
//    @NotNull
//   private String toAddress;

    @NotBlank
    @NotNull
    private String subject;

    @NotBlank
    @NotNull
   private String message;

    @NotBlank
    @NotNull
   private List<ViewedPostDetails> posts;
}
