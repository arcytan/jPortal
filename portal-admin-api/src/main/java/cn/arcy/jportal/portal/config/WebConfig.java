package cn.arcy.jportal.portal.config;

import cn.arcy.jportal.common.enums.AbstractEnum;
import cn.arcy.jportal.common.enums.JsonDeserializer;
import cn.arcy.jportal.portal.handler.CodeToEnumConverterFactory;
import com.fasterxml.jackson.databind.*;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;
import java.util.Set;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //增加Code转换成对应的枚举
        registry.addConverterFactory(new CodeToEnumConverterFactory());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Reflections reflections = new Reflections("cn.arcy.jportal");
        Set<Class<?>> jsonDeserializerWith = reflections.getTypesAnnotatedWith(JsonDeserializer.class);
        jsonDeserializerWith.forEach(clz -> {
            if (clz != AbstractEnum.class && AbstractEnum.class.isAssignableFrom(clz)) {
                System.out.println(clz);
            }
        });
    }
}
