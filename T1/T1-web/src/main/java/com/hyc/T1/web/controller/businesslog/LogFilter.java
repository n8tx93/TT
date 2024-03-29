package com.hyc.T1.web.controller.businesslog;

import org.openkoala.businesslog.utils.BusinessLogServletFilter;

import javax.servlet.*;

/**
 * User: zjzhai
 * Date: 11/27/13
 * Time: 11:01 AM
 */
public class LogFilter extends BusinessLogServletFilter {


    /**
     * 将需要用到的信息放入日志上下文
     *
     * @param req
     * @param resp
     * @param chain
     */
    @Override
    public void beforeFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        addIpContext(getIp(req));
        addUserContext(req.getParameter("user"));
    }


    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
