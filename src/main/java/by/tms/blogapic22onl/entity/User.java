package by.tms.blogapic22onl.entity;

import jakarta.persistence.*;
import lombok.*;
import org.eclipse.angus.mail.imap.protocol.ID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import by.tms.blogapic22onl.entity.post.Post;

import java.util.Set;

import javax.management.relation.Role;
import java.util.*;

@Entity
@ToString
@Setter
@Getter
@Table(name = "tb_user")
public class User extends AbstractEntity  {

    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String photo;
    private String country;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();


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
