package CS434.ExamPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableSpringConfigured
//@ComponentScan({"CS434.ExamPortal"})
//@EntityScan("com.delivery.domain")
//@EnableJpaRepositories("CS434.ExamPortal")
public class ExamPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}

}
