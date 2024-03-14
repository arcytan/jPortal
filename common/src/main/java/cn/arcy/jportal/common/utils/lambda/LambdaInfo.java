package cn.arcy.jportal.common.utils.lambda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

@Getter
@AllArgsConstructor
public class LambdaInfo<T> {

    private String methodName;

    private Class<T> clazz;
}
