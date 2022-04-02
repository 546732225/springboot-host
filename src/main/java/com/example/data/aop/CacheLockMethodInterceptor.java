package com.example.data.aop;

import com.example.data.annotation.CacheLock;
import com.example.data.core.CacheKeyGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;


@Order(2)
@Aspect
@Configuration
public class CacheLockMethodInterceptor {

    private final StringRedisTemplate stringRedisTemplate;
    private final CacheKeyGenerator cacheKeyGenerator;

    @Autowired
    public CacheLockMethodInterceptor(StringRedisTemplate stringRedisTemplate, CacheKeyGenerator cacheKeyGenerator) {
        this.cacheKeyGenerator = cacheKeyGenerator;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Pointcut(value = "@annotation(cacheLock)")
    public void pointCut(CacheLock cacheLock) {
    }

    @Around(value = "execution(public * * (..)) && pointCut(cacheLock)", argNames = "joinPoint,cacheLock")
    public Object interceptor(ProceedingJoinPoint joinPoint, CacheLock cacheLock) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        if (StringUtils.isEmpty(cacheLock.prefix())) {
            throw new RuntimeException("前缀不能为空");
        }
        //获取自定义key
        final String lockKey = cacheKeyGenerator.getLockKey(joinPoint);
        final Boolean success = stringRedisTemplate.execute(
                (RedisCallback<Boolean>) connection -> connection.set(lockKey.getBytes(), new byte[0], Expiration.from(cacheLock.expire(), cacheLock.timeUnit())
                        , RedisStringCommands.SetOption.SET_IF_ABSENT));
        if (!success) {
            // TODO 按理来说 我们应该抛出一个自定义的 CacheLockException 异常;这里偷下懒
            throw new RuntimeException("请勿重复请求");
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("系统异常");
        }
    }
}