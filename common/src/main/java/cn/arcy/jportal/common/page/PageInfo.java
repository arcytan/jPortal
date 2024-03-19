package cn.arcy.jportal.common.page;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Objects;

@Component
@RequestScope
public class PageInfo {

    private final HttpServletRequest request;

    @Autowired
    public PageInfo(HttpServletRequest request)
    {
        this.request = request;
    }

    public Integer getPageSize()
    {
        Integer pageSize = null;
        String pageSizeReq = this.request.getParameter("pageSize");
        if (Objects.nonNull(pageSizeReq)) {
            pageSize = Integer.parseInt(pageSizeReq);
        }

        return pageSize == null ? 20 : pageSize;
    }

    public Integer getPage()
    {
        Integer page = null;
        String pageReq = this.request.getParameter("page");
        if (Objects.nonNull(pageReq)) {
            page = Integer.parseInt(pageReq);
        }

        return page == null ? 0 : page;
    }
}
