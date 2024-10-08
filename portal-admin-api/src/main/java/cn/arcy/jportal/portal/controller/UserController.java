package cn.arcy.jportal.portal.controller;

import cn.arcy.jportal.common.utils.PageUtil;
import cn.arcy.jportal.portal.dto.UserDto;
import cn.arcy.jportal.portal.exception.UserNotFoundException;
import cn.arcy.jportal.portal.mapstruct.UserMapStruct;
import cn.arcy.jportal.portal.vo.UserVo;
import cn.arcy.jportal.user.domain.entity.User;
import cn.arcy.jportal.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@Api("用户管理")
@Slf4j
public class UserController {

    @Autowired
    UserMapStruct userMapStruct;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    @GetMapping()
    @ApiOperation("获取用户列表")
    public Page<UserVo> list()
    {
        Page<User> usersWithPage = userService.findAllWithPage();
        return PageUtil.toPage(usersWithPage, UserVo.class);
    }

    @PostMapping()
    @ApiOperation("新增用户")
    public UserVo add(@RequestBody UserDto userDto)
    {
        User entity = userMapStruct.toEntity(userDto);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return userMapStruct.toUserVo(userService.insert(entity));
    }

}
