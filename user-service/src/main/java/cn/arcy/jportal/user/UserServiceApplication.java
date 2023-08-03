package cn.arcy.jportal.user;

import cn.arcy.jportal.autoconfigure.jpa.EnableJpaForJPortal;
import cn.arcy.jportal.autoconfigure.jpa.TestService;
import jakarta.inject.Inject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class);
    }
}