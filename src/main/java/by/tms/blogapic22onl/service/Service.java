package by.tms.blogapic22onl.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

public interface Service<T,ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void remove(T entity);
    void removeById(ID id);
    void update(T entity);
}
