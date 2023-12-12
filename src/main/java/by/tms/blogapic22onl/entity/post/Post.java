package by.tms.blogapic22onl.entity.post;

import by.tms.blogapic22onl.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_post")
@Getter
@Setter
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

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false)
    private Set<PostSource> contentType = new HashSet<>();

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    @JoinColumn(name = "like_id", referencedColumnName = "id")
    private List<Like> likesList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private List<Comment> commentsList;

    @OneToMany(mappedBy = "post")
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private List<Tag> tagsList;

    @ManyToMany(mappedBy = "post")
    private Set<User> postViewers;

    @ManyToMany(mappedBy = "post")
    private Set<User> postReposters;

}
