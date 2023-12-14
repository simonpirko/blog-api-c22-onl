package by.tms.blogapic22onl.service;

import by.tms.blogapic22onl.entity.Role;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private  final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    
    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.USER);
        return userRepository.save(user);
    }

    public Optional<User>findById(Long id){
        return userRepository.findById(id);
    }
    
    public List<User> findAll(){
        return userRepository.findAll();
    }
    
     public void remove(User user){
        userRepository.delete(user);
    }
    
    public void removeById(Long id){
        if(id != null){
            userRepository.deleteById(id);
        }
    }
    
    public void update(User user) {
        if (user!= null){
            user.setName("new name");
            userRepository.save(user);
        }
    }
    
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException{
        return (UserDetails) userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User with this username isn't found"));
    }

    public void assignRoleToUser(User user, Role role){
        user.getRoles().add(role);
    }
}
