package project.singasong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SingASongApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingASongApiApplication.class, args);
    }

}
