package indipage.org.indipage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IndipageApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndipageApplication.class, args);
        System.out.println("print for test CI");
    }

}
