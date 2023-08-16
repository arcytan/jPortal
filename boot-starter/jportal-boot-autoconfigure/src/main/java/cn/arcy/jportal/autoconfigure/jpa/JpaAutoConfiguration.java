package cn.arcy.jportal.autoconfigure.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(basePackages = {"cn.arcy.jportal"})
public class JpaAutoConfiguration {

}
