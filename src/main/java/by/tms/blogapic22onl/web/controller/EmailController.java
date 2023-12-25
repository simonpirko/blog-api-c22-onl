package by.tms.blogapic22onl.web.controller;

import by.tms.blogapic22onl.dto.EmailDTO.EmailWithPostsDetails;
import by.tms.blogapic22onl.dto.EmailDTO.SimpleEmailDetails;
import by.tms.blogapic22onl.entity.Email;
import by.tms.blogapic22onl.service.emailService.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    // Sending a simple Email
    @PostMapping("/send_simple_email/{user_email}")
    public ResponseEntity sendMail(@RequestBody SimpleEmailDetails simpleEmailDetails, @PathVariable("user_email") String email){

        try {
            emailService.sendSimpleEmail(simpleEmailDetails);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Email was sent successfully", HttpStatus.OK);

    }

    // Sending email with attachment
    @PostMapping("/send_mail_with_posts/{user_email}")
    public ResponseEntity sendMailWithPosts(@RequestBody EmailWithPostsDetails emailWithPostsDetails, @PathVariable("user_email") String email){

        try {
            emailService.sendEmailWithPosts(emailWithPostsDetails);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Email was sent successfully", HttpStatus.OK);
    }
}
