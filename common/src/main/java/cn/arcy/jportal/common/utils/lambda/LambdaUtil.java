package cn.arcy.jportal.common.utils.lambda;

import cn.arcy.jportal.common.exceptions.LambdaParseException;
import cn.arcy.jportal.common.utils.ObjectUtil;
import cn.arcy.jportal.common.utils.function.FunctionSerializable;
import cn.hutool.core.lang.Assert;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
public class LambdaUtil {

    private static final Field FIELD_CAPTURING_CLASS;

    static {
        Field localField;
        try {
            Class<SerializedLambda> lambdaClass = SerializedLambda.class;
            localField = lambdaClass.getDeclaredField("capturingClass");
            localField.setAccessible(true);
        } catch (Throwable e) {
            log.warn(e.getMessage());
            localField = null;
        }
        FIELD_CAPTURING_CLASS = localField;
    }

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
        ClassLoader classLoader =
                FIELD_CAPTURING_CLASS != null ? ((Class<?>) FIELD_CAPTURING_CLASS.get(lambda)).getClassLoader() : null;
        return  Class.forName(className, true, classLoader);
    }
}
