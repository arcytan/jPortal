package cn.arcy.jportal.portal.controller;

import cn.arcy.jportal.common.utils.tree.TreeBuilder;
import cn.arcy.jportal.common.utils.tree.TreeModel;
import cn.arcy.jportal.permission.domain.entity.PermissionMenu;
import cn.arcy.jportal.permission.service.MenuService;
import cn.arcy.jportal.portal.mapstruct.PermissionMenuMapStruct;
import cn.arcy.jportal.portal.vo.MenuVo;
import io.swagger.annotations.Api;
import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/userMenu")
@RestController
@Api("用户菜单")
public class UserMenuController {

    @Inject
    MenuService menuService;

    @Inject
    PermissionMenuMapStruct menuMapStruct;

    @GetMapping("/")
    public List<TreeModel<MenuVo, Long>> getUserMenu()
    {
        List<PermissionMenu> menus = menuService.findAllEnabled();
        List<MenuVo> menuVoList = menuMapStruct.toMenuVoList(menus);

        //暂时不考虑权限，全部菜单返回
        TreeBuilder<MenuVo, Long> menuTreeBuilder = new TreeBuilder<>(menuVoList, MenuVo::getId, MenuVo::getParentId);
        return menuTreeBuilder.toTree();
    }
}
