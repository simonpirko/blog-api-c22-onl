package by.tms.blogapic22onl.dto.PostDTO;

import by.tms.blogapic22onl.dto.UserDTO.AuthorDetails;
import by.tms.blogapic22onl.entity.Comment;
import by.tms.blogapic22onl.entity.Like;
import by.tms.blogapic22onl.entity.Tag;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.entity.post.PostSource;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ViewedPostDetails {

    @NotBlank
    @NotEmpty
    private String title;

    @NotBlank
    @NotEmpty
    private String description;

    @NotBlank
    @NotEmpty
    private Set<PostSource> contentType;

    @NotBlank
    @NotEmpty
    private User user;

    @NotBlank
    @NotEmpty
    private LocalDateTime creationDate;

    @NotBlank
    @NotEmpty
    private List<Like> likesList;

    @NotBlank
    @NotEmpty
    private List<Comment> commentsList;

    @NotBlank
    @NotEmpty
    private List<Tag> tagsList;

    @NotBlank
    @NotEmpty
    private Set<AuthorDetails> postViewers;

    @NotBlank
    @NotEmpty
    private Set<AuthorDetails> postReposters;

    @NotBlank
    @NotNull
    private int countLikes;

    @NotBlank
    @NotNull
    private int countComments;

    @NotBlank
    @NotNull
    private int countViews;

    @NotBlank
    @NotNull
    private int countReposts;

}
