package by.tms.blogapic22onl.service.emailService;

import by.tms.blogapic22onl.dto.EmailDTO.EmailWithPostsDetails;
import by.tms.blogapic22onl.dto.EmailDTO.SimpleEmailDetails;
import by.tms.blogapic22onl.dto.PostDTO.ViewedPostDetails;
import by.tms.blogapic22onl.entity.post.Post;
import by.tms.blogapic22onl.service.AutoGenerationService;
import by.tms.blogapic22onl.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DefaultEmailService implements EmailService {

    public JavaMailSender emailSender;
    private final AutoGenerationService autoGenerationService;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;



    @Override
    public void sendSimpleEmail(SimpleEmailDetails simpleEmailDetails) throws MessagingException{

        MimeMessage simpleMailMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(simpleMailMessage, true, "UTF-8");

        messageHelper.setFrom(sender);
        messageHelper.setTo(simpleEmailDetails.getRecipient().getEmail());
        messageHelper.setSubject("Welcome, " + simpleEmailDetails.getRecipient().getName());

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

        messageHelper.setText(text, true);
        emailSender.send(simpleMailMessage);
    }


    @Override
    public void sendEmailWithPosts() throws MessagingException {
        EmailWithPostsDetails emailWithPostsDetails = new EmailWithPostsDetails();

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(sender);
        messageHelper.setTo(emailWithPostsDetails.getRecipient().getEmail());
        messageHelper.setSubject("Hello, " + emailWithPostsDetails.getRecipient().getName()+ "! We miss you");

        List<ViewedPostDetails> lovelyPosts = autoGenerationService.autoGenerationPosts(emailWithPostsDetails.getRecipient()).stream().limit(5).toList();
        emailWithPostsDetails.setPosts(lovelyPosts);

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
                      "<div>Hello, <b>" + emailWithPostsDetails.getRecipient().getName() + "</b></div>\n" +
                      "\n" +
                      "<div> Your haven't visited us since" + emailWithPostsDetails.getRecipient().getLastVisitDate() + "</div>\n" +
                      "<div> Look some interesting posts for you." + "</div>\n" +
                      "\n" +
                      "<div> " + emailWithPostsDetails.getPosts() + "/<div>" +
                      "</body>\n" +
                      "</html>\n";

        messageHelper.setText(text, true);

        emailSender.send(mimeMessage);
    }
}

