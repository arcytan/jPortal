package cn.arcy.jportal.portal.security;

import cn.arcy.jportal.portal.constant.JwtClaimNames;
import cn.arcy.jportal.portal.constant.RedisKey;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class RedisJwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (ObjectUtil.isNotNull(authentication)) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String token = (String) redisTemplate.opsForValue().get(RedisKey.getJwtTokenKey(jwt.getClaim(JwtClaimNames.CLIENT_ID), jwt.getTokenValue()));
            if (StrUtil.isEmpty(token)) {
                throw new InvalidBearerTokenException("用户认证失败！");
            }
        }

        filterChain.doFilter(request, response);
    }
}
