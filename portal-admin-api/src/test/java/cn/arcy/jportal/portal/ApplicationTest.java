package cn.arcy.jportal.portal;

import cn.arcy.jportal.permission.enums.MenuType;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class ApplicationTest {
    public static void main(String[] args) {
        MenuType[] values = MenuType.values();
        Stream.of(values).forEach(System.out::println);
    }
}
