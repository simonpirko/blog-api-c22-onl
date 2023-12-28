package by.tms.blogapic22onl.dto.UserDTO;

import by.tms.blogapic22onl.entity.AbstractEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
//@RequiredArgsConstructor
public class LoginUserDto {

    private AbstractEntity abstractEntity;

    public LoginUserDto() {
    }
    public LoginUserDto(AbstractEntity abstractEntity) {
        this.abstractEntity = abstractEntity;
    }

    @NotEmpty
    @NotBlank
    Long id = abstractEntity.getId();

    @NotEmpty
    @NotBlank
    private String username;

    @NotEmpty
    @NotBlank
    @Range(min = 4,max = 16)
    private String password;

    @NotEmpty
    @NotBlank
    private LocalDateTime lastVisitDate;
}
