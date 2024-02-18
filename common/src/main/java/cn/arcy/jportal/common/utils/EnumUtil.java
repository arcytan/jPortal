package cn.arcy.jportal.common.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import cn.arcy.jportal.common.enums.AbstractEnum;

public class EnumUtil {

    public static <T extends AbstractEnum> T codeOf(Class<T> enumClass, int code)
    {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(Objects::nonNull)
                .filter(o -> o.getCode() == code)
                .findFirst()
                .orElseThrow();
    }

    public static <T extends AbstractEnum> Map<Integer, String> codesMap(Class<T> enumClass)
    {
        return  Arrays.stream(enumClass.getEnumConstants())
                .collect(
                        Collectors.toMap(AbstractEnum::getCode, AbstractEnum::getDesc)
                );
    }
}
