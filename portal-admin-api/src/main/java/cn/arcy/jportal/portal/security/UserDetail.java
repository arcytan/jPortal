package cn.arcy.jportal.portal.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import cn.arcy.jportal.user.domain.entity.User;

import java.util.Collection;

public class UserDetail extends org.springframework.security.core.userdetails.User {

    User user;

    @Getter
    @Setter
    String token;

    public UserDetail(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

    public User getUserEntity()
    {
        return user;
    }
}
