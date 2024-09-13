package cn.arcy.jportal.portal.security;

import cn.arcy.jportal.portal.constant.JwtClaimNames;
import cn.arcy.jportal.portal.constant.RedisKey;
import cn.arcy.jportal.portal.exception.AuthenticationException;
import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    @Resource
    AuthenticationManager authenticationManager;

    @Autowired
    JwtEncoder jwtEncoder;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * token过期时间，单位：s
     */
    final long tokenExpiry = 3600L;

    /**
     * accessToken过期时间，单位：s
     */
    final long accessTokenExpiry = tokenExpiry * 24 * 7;

    public UserDetail login(String username, String password)
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (ObjectUtil.isNull(authenticate)) {
            throw new AuthenticationException("用户名或密码错误！");
        }
        String token = generateToken(authenticate);
        UserDetail userDetail = (UserDetail)authenticate.getPrincipal();
        userDetail.setToken(token);
        //用户登陆后，将token记录到redis中，用于登出的时候做判断
        redisTemplate.opsForValue().set(RedisKey.getJwtTokenKey(authenticate.getName(), token), token, Duration.ofSeconds(tokenExpiry));
        return userDetail;
    }

    public String generateToken(Authentication authentication, long expiry)
    {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(authentication.getName())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim(JwtClaimNames.CLIENT_ID, authentication.getName())
                //.claim("scope", scope)
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateToken(Authentication authentication)
    {
        return generateToken(authentication, tokenExpiry);
    }
}
