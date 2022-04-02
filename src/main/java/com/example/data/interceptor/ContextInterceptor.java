package com.example.data.interceptor;

import com.example.data.context.RequestContextHolder;
import com.example.data.util.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ContextInterceptor implements HandlerInterceptor {

    private final static String REQUEST_ID = "requestId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestId = UuidUtils.randomUUID();
        RequestContextHolder.getInstance().setRequestId(requestId);
        RequestContextHolder.getInstance().setRequest(request);
        RequestContextHolder.getInstance().setResponse(response);
        MDC.put(REQUEST_ID, requestId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        MDC.remove(REQUEST_ID);
        RequestContextHolder.getInstance().remove();
    }
}