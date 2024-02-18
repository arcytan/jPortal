package cn.arcy.jportal.portal.mapstruct;

import cn.arcy.jportal.permission.domain.entity.PermissionMenu;
import cn.arcy.jportal.portal.dto.MenuDto;
import cn.arcy.jportal.portal.vo.MenuVo;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        config = MapStructConfig.class
)
public interface PermissionMenuMapStruct {

    PermissionMenu toEntity(MenuDto menuDto);

    MenuDto toMenuDto(PermissionMenu permissionMenu);

    MenuVo toMenuVo(PermissionMenu permissionMenu);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PermissionMenu partialUpdate(MenuDto menuDto, @MappingTarget PermissionMenu permissionMenu);

}