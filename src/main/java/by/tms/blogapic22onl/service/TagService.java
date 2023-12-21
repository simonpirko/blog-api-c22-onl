package by.tms.blogapic22onl.service;

import by.tms.blogapic22onl.entity.Tag;
import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }


    public void remove(Tag tag) {
        tagRepository.delete(tag);
    }


    public Tag removeById(Long id) {
        Tag tagById = tagRepository.findById(id).orElseThrow();
            tagRepository.deleteById(id);

        return tagById;
    }

    @Transactional(readOnly = true)
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Tag> findByName(String name) {
        return Optional.of(tagRepository.findByName(name).orElseThrow());
    }


    @Transactional(readOnly = true)
    public List<Tag> findAllByPost(Post post){

        return tagRepository.findAllByPost(post);
    }


    public Tag update(Tag tag){
        return tagRepository.save(tag);
    }

}
