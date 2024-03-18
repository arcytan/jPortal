package cn.arcy.jportal.portal.controller;

import cn.arcy.jportal.portal.exception.UserNotFoundException;
import cn.arcy.jportal.portal.mapstruct.UserMapStruct;
import cn.arcy.jportal.portal.util.SessionContextUtil;
import cn.arcy.jportal.portal.vo.UserVo;
import cn.arcy.jportal.user.domain.entity.User;
import cn.arcy.jportal.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Api("用户管理")
public class UserController {

    @Autowired
    UserMapStruct userMapStruct;

    @Resource
    UserService userService;

    @GetMapping("/{id}")
    @ApiOperation("获取用户信息")
    public UserVo info(@PathVariable("id") Long id, Authentication authentication)
    {
        Optional<User> userOp = userService.findById(id);
        if (userOp.isEmpty()) {
            throw new UserNotFoundException();
        }
        UserVo userVo = userMapStruct.toUserVo(userOp.get());

        return userVo;
    }

    @GetMapping("/")
    @ApiOperation("获取用户列表")
    public Page<User> list()
    {
        Page<User> usersWithPage = userService.findAllWithPage();

    }

}
