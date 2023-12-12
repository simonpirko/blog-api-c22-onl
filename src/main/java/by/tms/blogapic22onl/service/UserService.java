package by.tms.blogapic22onl.service;

import by.tms.blogapic22onl.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserService implements Service<User, Long>{
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(User user) {

    }
}
