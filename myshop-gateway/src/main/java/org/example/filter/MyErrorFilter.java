package org.example.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.example.common.Result;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

//@Component
public class MyErrorFilter extends ZuulFilter {
    @Override
    public String filterType() { return FilterConstants.ERROR_TYPE; }
    @Override
    public int filterOrder() { return 0; }
    @Override
    public boolean shouldFilter() { return true; }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulException exception = (ZuulException) ctx.get("throwable");
        Result result = new Result(false, "执行失败：" + exception.getMessage());
        try {
            String jsonString = new ObjectMapper().writeValueAsString(result);
            ctx.getResponse().setContentType("text/json;charset=utf-8");
            ctx.getResponse().getWriter().write(jsonString);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}
