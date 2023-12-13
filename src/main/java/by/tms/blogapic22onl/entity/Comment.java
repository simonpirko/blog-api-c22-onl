package by.tms.blogapic22onl.entity;

import by.tms.blogapic22onl.entity.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_comment")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comment extends AbstractEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "text", nullable = false)
    private String text;
}
