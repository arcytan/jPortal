package cn.arcy.jportal.permission.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.TypeUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface ParameterizedTypeReference<T> {

    default Type getType() {
        final Type[] genericInterfaces = this.getClass().getGenericInterfaces();
        if (ArrayUtil.isNotEmpty(genericInterfaces)) {
            for (final Type genericInterface : genericInterfaces) {
                if (genericInterface instanceof ParameterizedType) {
                    Type[] types = ((ParameterizedType) genericInterface).getActualTypeArguments();
                    if (types.length >= 1) {
                        return types[0];
                    }
                }
            }
        }

        return null;
    }
}
