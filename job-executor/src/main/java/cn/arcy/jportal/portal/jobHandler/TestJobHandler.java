package cn.arcy.jportal.portal.jobHandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TestJobHandler {
    private Logger logger = LoggerFactory.getLogger(TestJobHandler.class);

    @XxlJob("testJobHandler")
    public void testJobHandler()
    {
        logger.info("11112222");
        XxlJobHelper.log("这是一个测试！");
    }
}
