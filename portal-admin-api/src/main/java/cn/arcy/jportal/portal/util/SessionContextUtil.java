package cn.arcy.jportal.portal.util;

import cn.arcy.jportal.common.utils.ApplicationContextUtil;
import cn.arcy.jportal.portal.exception.UserNotFoundException;
import cn.arcy.jportal.portal.security.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;

public class SessionContextUtil {

    /**
     * 获取当前用户登陆信息
     */
    public static UserDetail getUserDetail()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetail) {
            return (UserDetail) authentication.getPrincipal();
        }

        if (authentication.getPrincipal() instanceof Jwt jwt) {
            String subject = jwt.getSubject();
            UserDetailsService userDetailsService = ApplicationContextUtil.getBean(UserDetailsService.class);
            UserDetail userDetail = (UserDetail) userDetailsService.loadUserByUsername(subject);
            userDetail.setToken(jwt.getTokenValue());
            return userDetail;
        }

        throw new UserNotFoundException("用户不存在！");
    }

}
