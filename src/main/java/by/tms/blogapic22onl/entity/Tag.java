package by.tms.blogapic22onl.entity;

import by.tms.blogapic22onl.entity.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Tag extends AbstractEntity{

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    private List<Post> postsList;

}
