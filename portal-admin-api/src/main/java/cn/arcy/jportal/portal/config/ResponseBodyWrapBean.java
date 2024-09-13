package cn.arcy.jportal.portal.config;

import cn.arcy.jportal.portal.handler.RequestResponseBodyMethodHandler;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

//@Component
public class ResponseBodyWrapBean implements InitializingBean {

    @Autowired
    RequestMappingHandlerAdapter adapter;

    //@Value("${server.servlet.context-path}")
    String contextPath;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>();
        if (ObjectUtil.isNotNull(returnValueHandlers)) {
            handlers.addAll(returnValueHandlers);
        }

        for (HandlerMethodReturnValueHandler handler : handlers) {
            System.out.println(handler);
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                RequestResponseBodyMethodHandler returnValueHandler = new RequestResponseBodyMethodHandler(handler, contextPath);
                int index = handlers.indexOf(handler);
                handlers.set(index, returnValueHandler);
                break;
            }
        }

        adapter.setReturnValueHandlers(handlers);
    }
}
