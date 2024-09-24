package cn.arcy.jportal.portal.mapstruct;

import cn.arcy.jportal.portal.dto.UserDto;
import cn.arcy.jportal.portal.vo.UserVo;
import cn.arcy.jportal.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapStruct {

    User toEntity(UserDto userDto);

    UserVo toUserVo(User user);

    List<UserVo> toUserVoList(List<User> user);
}
