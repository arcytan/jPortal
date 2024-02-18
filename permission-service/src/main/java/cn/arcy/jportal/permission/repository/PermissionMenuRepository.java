package cn.arcy.jportal.permission.repository;

import cn.arcy.jportal.autoconfigure.jpa.repository.BaseRepository;
import cn.arcy.jportal.permission.domain.entity.PermissionMenu;
import org.springframework.stereotype.Repository;

public interface PermissionMenuRepository extends BaseRepository<PermissionMenu, Long> {

}
