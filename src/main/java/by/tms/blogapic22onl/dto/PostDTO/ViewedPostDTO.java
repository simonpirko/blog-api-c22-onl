package by.tms.blogapic22onl.dto.PostDTO;

import by.tms.blogapic22onl.entity.Comment;
import by.tms.blogapic22onl.entity.Like;
import by.tms.blogapic22onl.entity.Tag;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.entity.post.PostSource;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ViewedPostDTO {

    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    private String description;

    @NotBlank
    @NotNull
    private Set<PostSource> contentType;

    @NotBlank
    @NotNull
    private User user;

    @NotBlank
    @NotNull
    private LocalDateTime creationDate;

    @NotBlank
    @NotNull
    private List<Like> likesList;

    @NotBlank
    @NotNull
    private List<Comment> commentsList;

    @NotBlank
    @NotNull
    private List<Tag> tagsList;

    @NotBlank
    @NotNull
    private Set<User> postViewers;

    @NotBlank
    @NotNull
    private Set<User> postReposters;

}
