package by.tms.blogapic22onl.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)      //???????????????????????????????
    private List<View> view;

    @OneToMany(cascade = CascadeType.ALL)      //???????????????????????????????
    private List<Repost> repost;

}
