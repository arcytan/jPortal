package cn.arcy.jportal.common.utils.function;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 使用Lambda风格的入参
 * 配合工具类可以获取调用方法和调用类型
 * @param <T> 传入的对象类型
 * @param <R> 调用的方法类型
 */
@FunctionalInterface
public interface FunctionSerializable<T, R> extends Function<T, R>, Serializable {
}
