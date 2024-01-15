package cn.arcy.jportal.permission.enums;

import cn.arcy.jportal.permission.utils.ParameterizedTypeReference;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.TypeUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.stream.Stream;

public interface AbstractEnum<T> extends ParameterizedTypeReference<T> {
    public int getCode() ;

    public String getDesc();

    default AbstractEnum<?> codeOf(int code) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Type type = this.getType();
        Method method = ReflectUtil.getMethod((Class<?>) type, "values");
        AbstractEnum<?>[] values = (AbstractEnum<?>[])((Class<?>) type).getMethod("values").invoke(null);
        return Stream.of(values).filter(c -> c.getCode() == code).findFirst().orElseThrow();
    };
}
