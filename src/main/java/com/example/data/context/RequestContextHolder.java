package com.example.data.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RequestContextHolder {
    private static final ThreadLocal<HttpServletRequest> REQUEST = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletResponse> RESPONSE = new ThreadLocal<>();
    private static final ThreadLocal<String> REQUEST_ID = new ThreadLocal<>();
    private static final ThreadLocal<Long> REQUEST_TIMESTAMP = new ThreadLocal<>();

    private RequestContextHolder() {
    }

    public static RequestContextHolder getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String getRequestId() {
        return REQUEST_ID.get();
    }

    public void setRequestId(String value) {
        RequestContextHolder.REQUEST_ID.set(value);
    }


    public Long getRequestTimestamp() {
        return REQUEST_TIMESTAMP.get();
    }

    public HttpServletRequest getRequest() {
        return REQUEST.get();
    }

    public void setRequest(HttpServletRequest request) {
        RequestContextHolder.REQUEST.set(request);
        RequestContextHolder.REQUEST_TIMESTAMP.set(System.currentTimeMillis());
    }

    public HttpServletResponse getResponse() {
        return RESPONSE.get();
    }

    public void setResponse(HttpServletResponse response) {
        RequestContextHolder.RESPONSE.set(response);
    }

    public void remove() {
        RequestContextHolder.REQUEST_ID.remove();
        RequestContextHolder.REQUEST.remove();
        RequestContextHolder.RESPONSE.remove();
        RequestContextHolder.REQUEST_TIMESTAMP.remove();
    }

    private static class SingletonHolder {
        private static final RequestContextHolder INSTANCE = new RequestContextHolder();
    }
}
