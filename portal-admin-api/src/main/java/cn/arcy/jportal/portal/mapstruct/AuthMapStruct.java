package cn.arcy.jportal.portal.mapstruct;

import cn.arcy.jportal.portal.vo.AuthVo;
import cn.arcy.jportal.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapStruct {
    AuthVo toAuthVo(User user);
}
