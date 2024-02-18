package cn.arcy.jportal.permission.service;

import cn.arcy.jportal.common.exceptions.RecordNotExistException;
import cn.arcy.jportal.permission.domain.entity.PermissionMenu;
import cn.arcy.jportal.permission.repository.PermissionMenuRepository;
import jakarta.inject.Inject;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {

    @Inject
    private PermissionMenuRepository menuRepository;

    public PermissionMenu insert(PermissionMenu permissionMenu)
    {
        //根据parentId，计算录入菜单的层级
        permissionMenu.setLevel(calculateMenuLevel(permissionMenu.getParentId()));
        return menuRepository.save(permissionMenu);
    }

    public PermissionMenu update(PermissionMenu permissionMenu)
    {
        if (!existMenuById(permissionMenu.getId())) {
            throw new RecordNotExistException("菜单不存在，无法更新！");
        }

        return menuRepository.save(permissionMenu);
    }

    public Optional<PermissionMenu> find(Long id)
    {
        return menuRepository.findById(id);
    }

    public long countSubMenuById(Long id)
    {
        PermissionMenu menuEntity = new PermissionMenu();
        menuEntity.setParentId(id);
        return menuRepository.count(Example.of(menuEntity));
    }

    public boolean existMenuById(Long id)
    {
        return menuRepository.existsById(id);
    }

    public void delete(Long id)
    {
        //判断菜单是否有子菜单
        if (countSubMenuById(id) > 0) {
            throw new RecordNotExistException("存在子菜单，请先删除子菜单！");
        }
        menuRepository.deleteById(id);
    }

    protected Byte calculateMenuLevel(Long parentId)
    {
        //parentId为0，则为一级菜单
        if (parentId.compareTo(0L) <= 0) {
            return 1;
        }
        Optional<PermissionMenu> menuOptional = find(parentId);
        if (menuOptional.isEmpty()) {
            throw new RecordNotExistException("父菜单不存在！");
        }
        //子等级为父菜单+1
        return (byte) (menuOptional.get().getLevel() + 1);
    }
}
