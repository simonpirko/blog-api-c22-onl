package by.tms.blogapic22onl.repository;

import by.tms.blogapic22onl.entity.User;
import org.eclipse.angus.mail.imap.protocol.ID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, ID> {
    Optional<User> findByUsername(String username);



}


