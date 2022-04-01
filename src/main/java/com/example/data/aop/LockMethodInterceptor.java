package com.example.data.aop;

import com.example.data.annotation.LocalLock;
import com.example.data.exception.DuplicateRequestException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author seven
 */

@Aspect
@Component
public class LockMethodInterceptor {

    private static Cache<String, Object> CACHE = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .concurrencyLevel(Runtime.getRuntime().availableProcessors())
            .expireAfterWrite(5, TimeUnit.MINUTES).build();


    @Around("execution(public * *(..))  && @annotation(com.example.data.annotation.LocalLock)")
    public Object interceptor(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        LocalLock localLock = method.getAnnotation(LocalLock.class);
        String key = getKey(localLock.key(), joinPoint.getArgs());
        if (!StringUtils.isEmpty(key)) {
      
            if (CACHE.getIfPresent(key) != null) {
                throw new DuplicateRequestException("请勿重复请求！");
            }
            CACHE.put(key, key);
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("服务器异常");
        }
    }

    private String getKey(String keyExpress, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            keyExpress = keyExpress.replace("arg[" + i + "]", args[i].toString());
        }
        return keyExpress;
    }

}
