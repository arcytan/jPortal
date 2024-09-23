package cn.arcy.jportal.common.utils.lambda;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class LambdaInfo<T> {

    private String methodName;

    private Class<T> clazz;
}
