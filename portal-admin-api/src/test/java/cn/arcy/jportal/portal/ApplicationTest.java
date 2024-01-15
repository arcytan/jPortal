package cn.arcy.jportal.portal;

import cn.arcy.jportal.permission.enums.MenuType;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

@SpringBootTest
public class ApplicationTest {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        MenuType[] values = MenuType.values();
        System.out.println(MenuType.PAGE.codeOf(1));
    }
}
