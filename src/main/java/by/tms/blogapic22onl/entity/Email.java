package by.tms.blogapic22onl.entity;

import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Email extends AbstractEntity{

    private User recipient;
    private String message;
    private String subject;
    private String attachment;
    private List<ViewedPostDetails> posts;
}
