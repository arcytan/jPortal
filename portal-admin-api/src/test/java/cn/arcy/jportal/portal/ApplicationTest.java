package cn.arcy.jportal.portal;

import cn.arcy.jportal.permission.domain.entity.PermissionMenu;
import cn.arcy.jportal.permission.repository.PermissionMenuRepository;
import cn.arcy.jportal.common.utils.tree.TreeBuilder;
import cn.arcy.jportal.common.utils.tree.TreeModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    PermissionMenuRepository permissionMenuRepository;

    @Autowired
    ObjectMapper objectMapper;

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        /*MenuType[] values = MenuType.values();
        System.out.println(MenuType.PAGE.codeOf(1));*/

        //AbstractEnum.getInvokeClass();

        //System.out.println(EnumUtil.codeOf(MenuType.class, 2));

        /*List<One> list = new ArrayList<>();
        list.add(new One(1, "测试1"));
        list.add(new One(2, "测试2"));

        Optional<List<One>> list1 = Optional.of(list);
        list1.map(ones -> ones.get(0).message = "ddd").orElse("测试")*/;

    }

    @Test
    public void test() throws JsonProcessingException {
        List<PermissionMenu> menuList = permissionMenuRepository.findAll();
        TreeBuilder<PermissionMenu, Long> treeBuilder = new TreeBuilder<>(menuList, PermissionMenu::getId, PermissionMenu::getParentId);
        List<TreeModel<PermissionMenu, Long>> trees = treeBuilder.toTree();

        String s = objectMapper.writeValueAsString(trees);
    }

    public static class One
    {
        int code;
        String message;

        public One(int code, String message)
        {
            this.code = code;
            this.message = message;
        }
    }
}
