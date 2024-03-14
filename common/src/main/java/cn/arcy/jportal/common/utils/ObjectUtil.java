package cn.arcy.jportal.common.utils;

public class ObjectUtil {

    public static <T, N> N typeCast(T origin)
    {
        try {
            return (N) origin;
        } catch (Exception e) {
            throw new TypeNotPresentException(e.getMessage(), e);
        }
    }
}
