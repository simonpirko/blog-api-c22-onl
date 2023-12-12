package by.tms.blogapic22onl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_comment")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comment extends AbstractEntity{
}
