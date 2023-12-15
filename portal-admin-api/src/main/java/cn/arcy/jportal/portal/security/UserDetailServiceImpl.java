package cn.arcy.jportal.portal.security;

import cn.arcy.jportal.portal.exception.AuthenticationException;
import cn.arcy.jportal.user.domain.entity.User;
import cn.arcy.jportal.user.service.UserService;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

        return new UserDetail(user.get(), Collections.emptyList());
    }
}
