package by.tms.blogapic22onl.configuration;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
public class UserPrincipal implements UserDetails {

    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String photo;
    private String country;

    private Set<Role> roles ;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        {
            return (Collection<? extends GrantedAuthority>) new ArrayList<>(roles);
        }

    }
    @Override
    public  String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
