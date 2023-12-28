package by.tms.blogapic22onl.entity.post;

import by.tms.blogapic22onl.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_post")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "photo", nullable = false)
    private String photo;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false)
    private Set<PostSource> contentType = new HashSet<>();

    @Column(name = "creation_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd't'hh:mm:ss'z'")
    private LocalDateTime creationDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "like_id", referencedColumnName = "id")
    private List<Like> likesList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private List<Comment> commentsList;

    @ManyToMany(mappedBy = "postsList")
    @Size(max = 15)
    private List<Tag> tagsList;

    @ManyToMany
    private Set<User> postViewers;

    @ManyToMany
    private Set<User> postReposters;

    private int countLikes;
    private int countComments;
    private int countViews;
    private int countReposts;

}
