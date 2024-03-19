package cn.arcy.jportal.portal.handler;

import cn.arcy.jportal.common.utils.response.HttpResult;
import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * {@link RequestResponseBodyMethodProcessor} 的装饰类
 * 用于包装一个合适的API返回格式
 * 目前已经被取消使用，改用@RestControllerAdvice实现
 */
@Deprecated
@Slf4j
public class RequestResponseBodyMethodHandler implements HandlerMethodReturnValueHandler {

    final HandlerMethodReturnValueHandler handler;

    final String contextPath;

    public RequestResponseBodyMethodHandler(HandlerMethodReturnValueHandler handler, String contextPath) {
        this.handler = handler;
        this.contextPath = contextPath;
    }

    public RequestResponseBodyMethodHandler(HandlerMethodReturnValueHandler handler)
    {
        this(handler, null);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return handler.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        String uri = ((HttpServletRequest) webRequest.getNativeRequest()).getRequestURI();
        System.out.println("测试统一格式返回！");
        /**
         * 执行原则：
         * 如果指定路径为空或检查到uri存在指定路径
         * 则执行固定格式返回的json
         * 否则执行装饰器持有的原生方法
         */
        if (ObjectUtil.isNull(contextPath) || contextPath.isEmpty() || uri.startsWith(contextPath)) {
            returnValue = HttpResult.builder().code(HttpStatus.OK.value()).message("成功！").data(returnValue).build();
        }

        handler.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
}
