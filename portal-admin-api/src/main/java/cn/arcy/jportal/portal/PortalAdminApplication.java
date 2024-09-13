package cn.arcy.jportal.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"cn.arcy.jportal"})
public class PortalAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalAdminApplication.class);
    }
}