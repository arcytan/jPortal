package cn.arcy.jportal.user;

import cn.arcy.jportal.user.domain.entity.User;
import cn.arcy.jportal.user.repository.UserRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserTest {

    @Inject
    UserRepository userRepository;

    @Test
    public void UserGetOneTest() {
        Optional<User> userOp = userRepository.findById(36L);
        System.out.println(userOp);
    }
}
