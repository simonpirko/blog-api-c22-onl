package by.tms.blogapic22onl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_view")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class View extends AbstractEntity{
}
