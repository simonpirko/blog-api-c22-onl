package by.tms.blogapic22onl.repository;


import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface Repository <T, ID>{
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void remove(T entity);
    void removeById(ID id);
    void update(T entity);

}
