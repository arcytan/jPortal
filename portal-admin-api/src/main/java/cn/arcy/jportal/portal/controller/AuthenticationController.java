package cn.arcy.jportal.portal.controller;

import cn.arcy.jportal.portal.constant.JwtClaimNames;
import cn.arcy.jportal.portal.constant.RedisKey;
import cn.arcy.jportal.portal.dto.AuthDto;
import cn.arcy.jportal.portal.exception.UserNotFoundException;
import cn.arcy.jportal.portal.mapstruct.AuthMapStruct;
import cn.arcy.jportal.portal.security.AuthenticationService;
import cn.arcy.jportal.portal.security.UserDetail;
import cn.arcy.jportal.portal.util.HttpResult;
import cn.arcy.jportal.portal.util.SessionContextUtil;
import cn.arcy.jportal.portal.vo.AuthVo;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@Api("用户认证")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AuthMapStruct authMapStruct;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/login")
    @ApiOperation("登陆")
    public AuthVo login(@Validated @RequestBody AuthDto authDto, Authentication authentication)
    {
        //已登录用户不能重复登陆
        if (ObjectUtil.isNotNull(authentication) && authentication.isAuthenticated()) {
            throw new RuntimeException("用户已登录！");
        }

        UserDetail userDetail = authenticationService.login(authDto.getUsername(), authDto.getPassword());
        AuthVo authVo = authMapStruct.toAuthVo(userDetail.getUserEntity());
        authVo.setToken(userDetail.getToken());
        return authVo;
    }

    @PostMapping("/logout")
    @ApiOperation("登出")
    public HttpResult<?> logout()
    {
        //将当前用户所有的jwt从redis中删除
        //获取当前用户所有jwt
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (ObjectUtil.isNull(authentication)) {
            throw new UserNotFoundException("用户已登出！");
        }
        Jwt principal = (Jwt) authentication.getPrincipal();
        String jwtPrefix = RedisKey.getJwtTokenPrefix(principal.getClaim(JwtClaimNames.CLIENT_ID));
        Set<String> keys = redisTemplate.keys(jwtPrefix + "*");
        redisTemplate.delete(keys);
        return HttpResult.builder().code(HttpStatus.OK.value()).message("登出成功！").build();
    }

    @PostMapping("/token")
    @ApiOperation("获取新的token")
    public HttpResult<?> token(Authentication authentication)
    {
        Map<String, Object> tokenData = Map.of("token", authenticationService.generateToken(authentication));
        return HttpResult.Ok("获取成功！", tokenData);
    }
}
