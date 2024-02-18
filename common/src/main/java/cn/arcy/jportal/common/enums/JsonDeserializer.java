package cn.arcy.jportal.common.enums;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface JsonDeserializer {
}
