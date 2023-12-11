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

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
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

    @Column(name = "view")      //???????????????????????????????
    private int view;

    @Column(name = "repost")      //???????????????????????????????
    private int repost;

}
