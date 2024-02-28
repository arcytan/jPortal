package cn.arcy.jportal.jpa;

import cn.arcy.jportal.jpa.repository.BaseJpaRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(basePackages = {"cn.arcy.jportal"}, repositoryFactoryBeanClass = BaseJpaRepositoryFactoryBean.class)
public class JpaAutoConfiguration {

}
