package cn.arcy.jportal.autoconfigure.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories
public class JpaAutoConfiguration {
    @Bean(name = "jpaTestService")
    public TestService test()
    {
        System.out.println("我是测试的starter");
        return new TestService();
    }
}
