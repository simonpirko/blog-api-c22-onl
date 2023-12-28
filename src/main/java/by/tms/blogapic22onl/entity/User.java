package by.tms.blogapic22onl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.eclipse.angus.mail.imap.protocol.ID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import by.tms.blogapic22onl.entity.post.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.*;

@Entity
@ToString
@Setter
@Getter
@Builder
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity  {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "photo")
    private String photo;

    @Column(name = "country", nullable = false)
    private String country;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private List<Post> postsList;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private List<Like> likesList;

  
  @ManyToMany
    @JoinTable(name = "post_views",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Post> viewedPosts;

    @ManyToMany
            @JoinTable(name = "post_reposts",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Post> repostedPosts;


    @Column(name = "last_visit")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd't'hh:mm:ss'z'")
    private LocalDateTime lastVisitDate;
}
