package by.tms.blogapic22onl.entity;

import by.tms.blogapic22onl.entity.post.Post;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_like")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Like extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime localDateTime;
}
