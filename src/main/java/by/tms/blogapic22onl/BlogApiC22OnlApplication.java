package by.tms.blogapic22onl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public abstract class BlogApiC22OnlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApiC22OnlApplication.class, args);
	}

//	@Scheduled(cron = "${interval-in-cron}")
//	public abstract void sendEmailWithPosts();

}
