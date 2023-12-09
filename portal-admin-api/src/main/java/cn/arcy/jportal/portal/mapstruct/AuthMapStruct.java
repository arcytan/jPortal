package cn.arcy.jportal.portal.mapstruct;

import cn.arcy.jportal.portal.vo.AuthVo;
import cn.arcy.jportal.user.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapStruct {
    AuthVo toAuthVo(User user);
}
