package cn.arcy.jportal.portal.mapstruct;

import cn.arcy.jportal.portal.dto.UserDto;
import cn.arcy.jportal.portal.security.UserDetail;
import cn.arcy.jportal.portal.vo.UserVo;
import cn.arcy.jportal.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapStruct {

    User fromUserDto(UserDto userDto);

    UserVo toUserVo(User user);

    List<UserVo> toUserVoList(List<User> user);
}
