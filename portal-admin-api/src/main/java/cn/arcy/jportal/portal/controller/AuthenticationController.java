package cn.arcy.jportal.portal.controller;

import cn.arcy.jportal.portal.dto.AuthDto;
import cn.arcy.jportal.portal.mapstruct.AuthMapStruct;
import cn.arcy.jportal.portal.security.AuthenticationService;
import cn.arcy.jportal.portal.security.UserDetail;
import cn.arcy.jportal.portal.vo.AuthVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户认证")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AuthMapStruct authMapStruct;

    @PostMapping("/login")
    @ApiOperation("登陆")
    public AuthVo login(AuthDto authDto)
    {
        UserDetail userDetail = authenticationService.login(authDto.getUsername(), authDto.getPassword());
        AuthVo authVo = authMapStruct.toAuthVo(userDetail.getUserEntity());
        authVo.setToken(userDetail.getToken());
        return authVo;
    }
}
