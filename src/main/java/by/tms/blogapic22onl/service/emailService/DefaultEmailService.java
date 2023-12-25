package by.tms.blogapic22onl.service.emailService;

import by.tms.blogapic22onl.dto.EmailDTO.EmailWithPostsDetails;
import by.tms.blogapic22onl.dto.EmailDTO.SimpleEmailDetails;
import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import by.tms.blogapic22onl.entity.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Service
@RequiredArgsConstructor
public class DefaultEmailService implements EmailService {

    public JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendSimpleEmail(SimpleEmailDetails simpleEmailDetails) throws MessagingException{

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(simpleEmailDetails.getRecipient().getEmail());
        simpleMailMessage.setSubject("Welcome, " + simpleEmailDetails.getRecipient().getName());

        String text = "<!doctype html>\n" +
                      "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
                      "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
                      "<head>\n" +
                      "    <meta charset=\"UTF-8\">\n" +
                      "    <meta name=\"viewport\"\n" +
                      "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                      "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                      "    <title>Email</title>\n" +
                      "</head>\n" +
                      "<body>\n" +
                      "<div>Welcome, <b>" + simpleEmailDetails.getRecipient().getName() + "</b></div>\n" +
                      "\n" +
                      "<div> Your username is <b>" + simpleEmailDetails.getRecipient().getUsername() + "</b></div>\n" +
                      "<div> Your password is <b>" + simpleEmailDetails.getRecipient().getPassword() + "</b></div>\n" +
                      "\n" +
                      "</body>\n" +
                      "</html>\n";
        simpleMailMessage.setText(text);
        emailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailWithPosts(EmailWithPostsDetails emailWithPostsDetails) throws MessagingException {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setTo(emailWithPostsDetails.getRecipient().getEmail());
        messageHelper.setSubject(emailWithPostsDetails.getSubject());
        messageHelper.setText(emailWithPostsDetails.getMessage(), true);
//        messageHelper.setText(emailWithPostsDetails.getPosts().toString());
        emailSender.send(mimeMessage);
    }
}

