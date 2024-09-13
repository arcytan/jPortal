package cn.arcy.jportal.portal.config;

import cn.arcy.jportal.portal.handler.CodeToEnumConverterFactory;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

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

    }
}
