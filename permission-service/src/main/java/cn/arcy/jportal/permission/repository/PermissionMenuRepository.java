package cn.arcy.jportal.permission.repository;

import cn.arcy.jportal.jpa.repository.BaseRepository;
import cn.arcy.jportal.permission.domain.entity.PermissionMenu;

import java.util.List;

public interface PermissionMenuRepository extends BaseRepository<PermissionMenu, Long> {

    public List<PermissionMenu> findAllByDisabledOrderBySortAsc(boolean disabled);
}
