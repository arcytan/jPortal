package cn.arcy.jportal.user.service;

import cn.arcy.jportal.user.domain.entity.User;
import cn.arcy.jportal.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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

    public List<User> selectAll()
    {
        return userRepository.findAll();
    }
}
