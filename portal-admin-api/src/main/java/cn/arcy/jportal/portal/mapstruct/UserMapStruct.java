package cn.arcy.jportal.portal.mapstruct;

import cn.arcy.jportal.portal.dto.UserDto;
import cn.arcy.jportal.portal.vo.UserVo;
import cn.arcy.jportal.user.domain.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapStruct {

    User toEntity(UserDto userDto);

    UserVo toUserVo(User user);

    List<UserVo> toUserVoList(List<User> user);
}
