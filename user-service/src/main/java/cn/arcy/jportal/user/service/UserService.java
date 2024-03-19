package cn.arcy.jportal.user.service;

import cn.arcy.jportal.jpa.AbstractService;
import cn.arcy.jportal.user.domain.entity.User;
import cn.arcy.jportal.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends AbstractService<UserRepository, User, Long> {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserRepository getRepository() {
        return this.userRepository;
    }

    public User save(User user)
    {
        return userRepository.save(user);
    }

    public Optional<User> find(String username, String password)
    {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.findOne(Example.of(user));
    }

    public Optional<User> find(String username)
    {
        User user = new User();
        user.setUsername(username);
        return userRepository.findOne(Example.of(user));
    }

    public Optional<User> findById(Long id)
    {
        return userRepository.findById(id);
    }

    public List<User> findAll(PageRequest pageRequest)
    {
        return userRepository.findAll();
    }

    public User insert(User user)
    {
        //判断用户邮箱和用户名的唯一性
        if (existsByUserName(user.getUsername())) {
            throw new IllegalArgumentException("用户名已存在！");
        }

        if (existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("邮箱已存在！");
        }
        return this.userRepository.save(user);
    }

    public boolean existsByUserName(String username)
    {
        User user = new User();
        user.setUsername(username);
        return this.userRepository.exists(Example.of(user));
    }

    public boolean existsByEmail(String email)
    {
        User user = new User();
        user.setEmail(email);
        return this.userRepository.exists(Example.of(user));
    }
}
