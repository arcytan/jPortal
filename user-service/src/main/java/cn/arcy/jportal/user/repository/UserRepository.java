package cn.arcy.jportal.user.repository;

import cn.arcy.jportal.autoconfigure.jpa.repository.BaseRepository;
import cn.arcy.jportal.user.domain.entity.User;

public interface UserRepository extends BaseRepository<User, Long> {
}
