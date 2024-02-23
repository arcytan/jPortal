package cn.arcy.jportal.portal.mapstruct;

import cn.arcy.jportal.permission.domain.entity.PermissionMenu;
import cn.arcy.jportal.portal.dto.MenuDto;
import cn.arcy.jportal.portal.dto.MenuUpdateDto;
import cn.arcy.jportal.portal.vo.MenuVo;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissionMenuMapStruct {

    PermissionMenu toEntity(MenuDto menuDto);

    PermissionMenu toEntity(MenuUpdateDto menuUpdateDto);

    MenuDto toMenuDto(PermissionMenu permissionMenu);

    MenuVo toMenuVo(PermissionMenu permissionMenu);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PermissionMenu partialUpdate(MenuDto menuDto, @MappingTarget PermissionMenu permissionMenu);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PermissionMenu partialUpdate(MenuUpdateDto menuDto, @MappingTarget PermissionMenu permissionMenu);

}