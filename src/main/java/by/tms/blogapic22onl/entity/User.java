package by.tms.blogapic22onl.entity;

import by.tms.blogapic22onl.entity.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends AbstractEntity{

    @ManyToMany
    @JoinTable(name = "post_views",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<Post> viewedPosts;

    @ManyToMany
            @JoinTable(name = "post_reposts",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<Post> repostedPosts;


}
