package cn.arcy.jportal.portal.mapstruct;

import cn.arcy.jportal.permission.domain.entity.PermissionMenu;
import cn.arcy.jportal.portal.dto.MenuDto;
import cn.arcy.jportal.portal.dto.MenuUpdateDto;
import cn.arcy.jportal.portal.vo.MenuVo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMenuMapStruct {

    PermissionMenu toEntity(MenuDto menuDto);

    PermissionMenu toEntity(MenuUpdateDto menuUpdateDto);

    MenuDto toMenuDto(PermissionMenu permissionMenu);

    MenuVo toMenuVo(PermissionMenu permissionMenu);

    List<MenuVo> toMenuVoList(List<PermissionMenu> permissionMenuList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PermissionMenu partialUpdate(MenuDto menuDto, @MappingTarget PermissionMenu permissionMenu);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PermissionMenu partialUpdate(MenuUpdateDto menuDto, @MappingTarget PermissionMenu permissionMenu);

}