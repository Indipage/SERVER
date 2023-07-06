package indipage.org.indipage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IndipageApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndipageApplication.class, args);
    }

}
