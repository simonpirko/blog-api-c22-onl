package by.tms.blogapic22onl.dto.UserDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@ToString
public class LoginUserDto {

    @NotEmpty
    @NotBlank
    private String username;

    @NotEmpty
    @NotBlank
    @Range(min = 4,max = 16)
    private String password;

    public  LoginUserDto(){}



}
