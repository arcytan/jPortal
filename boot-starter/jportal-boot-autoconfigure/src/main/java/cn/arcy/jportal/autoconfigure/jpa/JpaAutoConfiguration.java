package cn.arcy.jportal.autoconfigure.jpa;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
//@AutoConfiguration
//@EnableJpaRepositories
@ConditionalOnClass(TestService.class)
public class JpaAutoConfiguration {
    @Bean(name = "jpaTestService")
    public TestService test()
    {
        System.out.println("我是测试的starter");
        return new TestService();
    }
}
