package cn.arcy.jportal.common.utils.lambda;

import cn.arcy.jportal.common.exceptions.LambdaParseException;
import cn.arcy.jportal.common.utils.function.FunctionSerializable;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LFUCache;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
public final class LambdaUtil {

    private LambdaUtil(){}

    private static final LFUCache<Class<?>, LambdaInfo<?>> CACHE = CacheUtil.newLFUCache(16, DateUnit.SECOND.getMillis());

    @SuppressWarnings("unchecked")
    public static <T, R> LambdaInfo<T> parse(FunctionSerializable<T, R> func)
    {
        try {
            Method method = func.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            SerializedLambda lambda = (SerializedLambda) method.invoke(func);
            Class<?> instantiatedMethodTypeClass = getInstantiatedMethodTypeClass(lambda);
            Assert.notNull(instantiatedMethodTypeClass, "无法获取lambda表达式的具体类型！");

            return new LambdaInfo<>(lambda.getImplMethodName(), (Class<T>) instantiatedMethodTypeClass);
        } catch (Throwable e) {
            throw new LambdaParseException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T, R> LambdaInfo<T> parseWithCache(FunctionSerializable<T, R> func)
    {
        Class<?> clazz = func.getClass();
        LambdaInfo<T> lambdaInfo = (LambdaInfo<T>) CACHE.get(clazz);
        if (Objects.isNull(lambdaInfo)) {
            synchronized (CACHE) {
                lambdaInfo = (LambdaInfo<T>) CACHE.get(clazz);
                if (Objects.isNull(lambdaInfo)) {
                    lambdaInfo = parse(func);
                    CACHE.put(clazz, lambdaInfo);
                }
            }
        }

        return lambdaInfo;
    }

    /**
     * 获取lambda的实现的类
     * @param lambda 表达式 eg: Stu::getName
     * @return 实现的类 eg: Stu
     * @throws IllegalAccessException 权限异常
     */
    private static Class<?> getInstantiatedMethodTypeClass(SerializedLambda lambda) throws IllegalAccessException, ClassNotFoundException {
        String instantiatedMethodType = lambda.getInstantiatedMethodType();
        String pathName = instantiatedMethodType.substring(2, instantiatedMethodType.indexOf(';'));
        String className = pathName.replace("/", ".");
        return  Class.forName(className);
    }
}
