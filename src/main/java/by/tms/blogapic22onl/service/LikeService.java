package by.tms.blogapic22onl.service;

import by.tms.blogapic22onl.entity.Like;
import by.tms.blogapic22onl.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public Like save(Like like) {
        like.setLocalDateTime(LocalDateTime.now());
        return likeRepository.save(like);
    }

    public void removeById(Long id) {
        Optional<Like> like = Optional.ofNullable(likeRepository.findById(id).
                orElseThrow(RuntimeException::new));
        if (like.isPresent()) likeRepository.deleteById(id);
    }

    public List<Like> findAll() {
        return likeRepository.findAll();
    }
}
