package cn.arcy.jportal.portal.security;

import cn.arcy.jportal.portal.exception.UserNotFoundException;
import cn.arcy.jportal.user.domain.entity.User;
import cn.arcy.jportal.user.service.UserService;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StrUtil.isEmpty(username)) {
            throw new IllegalArgumentException("用户名不能为空！");
        }
        Optional<User> user = userService.find(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        User userEntity = user.get();
        if (userEntity.getDisabled()) {
            throw new UserNotFoundException("用户已被禁止！");
        }

        return new UserDetail(userEntity, Collections.emptyList());
    }
}
