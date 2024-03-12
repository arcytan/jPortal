package cn.arcy.jportal.portal.controller;

import cn.arcy.jportal.permission.repository.PermissionMenuRepository;
import cn.arcy.jportal.portal.vo.UserMenuVo;
import io.swagger.annotations.Api;
import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/userMenu")
@RestController
@Api("用户菜单")
public class UserMenuController {

    @Inject
    PermissionMenuRepository menuRepository;

    @GetMapping("/")

    public UserMenuVo getUserMenu()
    {

    }
}
