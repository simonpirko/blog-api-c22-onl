package by.tms.blogapic22onl.service.emailService;

import by.tms.blogapic22onl.dto.EmailDTO.EmailWithPostsDetails;
import by.tms.blogapic22onl.dto.EmailDTO.SimpleEmailDetails;
import by.tms.blogapic22onl.entity.Email;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;

public interface EmailService {
    void sendSimpleEmail(SimpleEmailDetails simpleEmailDetails) throws MessagingException;
    void sendEmailWithPosts() throws MessagingException;
}
