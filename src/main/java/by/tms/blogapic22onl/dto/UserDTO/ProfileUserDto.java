package by.tms.blogapic22onl.dto.UserDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;



public class ProfileUserDto {

    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    private String surname;

    @NotEmpty
    @NotBlank
    private String username;
    @NotEmpty
    @NotBlank
    private String email;
    @NotEmpty
    @NotBlank
    private String photo;

    @NotEmpty
    @NotBlank
    private String country;

    @NotEmpty
    @NotBlank
    private String role;


    public  ProfileUserDto(){}

}
