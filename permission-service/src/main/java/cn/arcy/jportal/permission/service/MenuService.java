package cn.arcy.jportal.permission.service;

import cn.arcy.jportal.common.exceptions.RecordNotExistException;
import cn.arcy.jportal.permission.domain.entity.PermissionMenu;
import cn.arcy.jportal.permission.enums.MenuType;
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
        //父级菜单不能少于1！
        if (permissionMenu.getParentId().compareTo(0L) < 0) {
            permissionMenu.setParentId(0L);
        }

        if (permissionMenu.getParentId().compareTo(0L) > 0) {
            Optional<PermissionMenu> menuOptional = find(permissionMenu.getParentId());
            PermissionMenu parentMenu = menuOptional.orElseThrow(() -> new RecordNotExistException("父级菜单不存在！"));
            if (parentMenu.getType() == MenuType.BUTTON) {
                throw new IllegalArgumentException("按钮菜单不能添加子菜单！");
            }
        }
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
}
