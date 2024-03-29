package cn.arcy.jportal.jpa;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(JpaAutoConfiguration.class)
public @interface EnableJpaForJPortal {
}
