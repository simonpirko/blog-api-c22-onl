package by.tms.blogapic22onl.dto.EmailDTO;

import by.tms.blogapic22onl.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class SimpleEmailDetails {

    @NotBlank
    @NotNull
    private User recipient;

//    @NotBlank
//    @NotNull
//    private String toAddress;

    @NotBlank
    @NotNull
    private String subject;

    @NotBlank
    @NotNull
    private String message;
}
