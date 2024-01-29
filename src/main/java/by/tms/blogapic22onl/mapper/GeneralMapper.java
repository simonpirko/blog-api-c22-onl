package by.tms.blogapic22onl.mapper;

import by.tms.blogapic22onl.dto.JwtAuthDTO.JWTAuthRequestDTO;
import by.tms.blogapic22onl.dto.JwtAuthDTO.JWTAuthResponseDTO;
import by.tms.blogapic22onl.dto.PostDTO.CreatedPostDetails;
import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import by.tms.blogapic22onl.dto.UserDTO.LoginUserDto;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.entity.post.Post;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface GeneralMapper{
    CreatedPostDetails mapToCreatedPostDetails(Post post);
    ViewedPostDetails mapToViewedPostDetails(Post post);
    Post mapToPost(CreatedPostDetails createdPostDetails);
    Post mapToPost(ViewedPostDetails viewedPostDetails);


    User mapToUser(LoginUserDto loginUserDto);
    LoginUserDto mapToLoginUserDto(User user);


    User mapToUser(JWTAuthRequestDTO JWTAuthRequestDTO);
    JWTAuthResponseDTO mapToJWTAuthResponseDTO(User user);

}

