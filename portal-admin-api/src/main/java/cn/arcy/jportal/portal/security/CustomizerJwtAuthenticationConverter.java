package cn.arcy.jportal.portal.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * 自定义实现Jwt的转换程序
 * 将Jwt带有的信息转换为UserDetails
 * 实现比较复杂，需要改写多个Jwt的处理类，
 * 因此改用获取Jwt后自己判断实现
 * 通过改造内部JWT来实现转换，好处在于能够通过Authentication注入就拿到UserDetails
 */
public class CustomizerJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        return null;
    }
}
