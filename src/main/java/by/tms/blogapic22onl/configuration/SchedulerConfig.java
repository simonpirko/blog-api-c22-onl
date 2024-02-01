package by.tms.blogapic22onl.configuration;

import by.tms.blogapic22onl.dto.EmailDTO.EmailWithPostsDetails;
import by.tms.blogapic22onl.entity.Email;
import by.tms.blogapic22onl.entity.User;
import by.tms.blogapic22onl.mapper.GeneralMapper;
import by.tms.blogapic22onl.service.UserService;
import by.tms.blogapic22onl.service.emailService.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Configuration
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
public class SchedulerConfig {

    private final EmailService emailService;
    private final UserService userService;

    @Scheduled(cron = "${interval-in-cron}")
    public void sendEmailWithPosts(){
        EmailWithPostsDetails email = new EmailWithPostsDetails();

            List<User> allUsersWithDelay = userService.findAll().stream()
                .filter(userService::getUserDelay)
                .toList();

            allUsersWithDelay.forEach(e -> {
                try {
                    emailService.sendEmailWithPosts();
                } catch (MessagingException ex) {
                    throw new RuntimeException(ex);
                }
            });

    }


}
