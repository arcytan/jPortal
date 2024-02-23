package cn.arcy.jportal.portal.controller;

import cn.arcy.jportal.permission.domain.entity.PermissionMenu;
import cn.arcy.jportal.permission.service.MenuService;
import cn.arcy.jportal.portal.dto.MenuDto;
import cn.arcy.jportal.portal.dto.MenuUpdateDto;
import cn.arcy.jportal.portal.exception.DefaultHttpException;
import cn.arcy.jportal.portal.exception.RecordNotFoundException;
import cn.arcy.jportal.portal.mapstruct.PermissionMenuMapStruct;
import cn.arcy.jportal.portal.util.HttpResult;
import cn.arcy.jportal.portal.vo.MenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.inject.Inject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/menu")
@Api("菜单管理")
public class MenuController {

    @Inject
    private MenuService menuService;

    @Inject
    private PermissionMenuMapStruct menuMapStruct;

    @PostMapping("/")
    @ApiOperation("新增菜单")
    public MenuVo add(@Validated @RequestBody MenuDto menuDto)
    {
        PermissionMenu entity = menuService.insert(menuMapStruct.toEntity(menuDto));
        return menuMapStruct.toMenuVo(entity);
    }

    @GetMapping("/{id}")
    @ApiOperation("菜单详情")
    public MenuVo info(@PathVariable("id") Long id)
    {
        Optional<PermissionMenu> menuOptional = menuService.find(id);
        if (menuOptional.isEmpty()) {
            throw new RecordNotFoundException("菜单不存在！");
        }

        return menuMapStruct.toMenuVo(menuOptional.get());
    }

    @PutMapping("/{id}")
    @ApiOperation("修改菜单")
    public MenuVo update(@PathVariable("id") Long id, @RequestBody MenuUpdateDto menuUpdateDto)
    {
        PermissionMenu entity = menuMapStruct.toEntity(menuUpdateDto);
        entity.setId(id);
        entity = menuService.update(entity);
        return menuMapStruct.toMenuVo(entity);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除菜单")
    public HttpResult<?> delete(@PathVariable("id") Long id) {
        try {
            menuService.delete(id);
            return HttpResult.Ok("菜单删除成功！");
        } catch (RuntimeException e) {
            throw new DefaultHttpException(e.getMessage());
        }
    }
}
