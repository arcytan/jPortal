package cn.arcy.jportal.portal;

import cn.arcy.jportal.common.utils.EnumUtil;
import cn.arcy.jportal.permission.enums.MenuType;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ApplicationTest {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        /*MenuType[] values = MenuType.values();
        System.out.println(MenuType.PAGE.codeOf(1));*/

        //AbstractEnum.getInvokeClass();

        //System.out.println(EnumUtil.codeOf(MenuType.class, 2));

        List<One> list = new ArrayList<>();
        list.add(new One(1, "测试1"));
        list.add(new One(2, "测试2"));

        Optional<List<One>> list1 = Optional.of(list);
        list1.map(ones -> ones.get(0).message = "ddd").orElse("测试");
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
