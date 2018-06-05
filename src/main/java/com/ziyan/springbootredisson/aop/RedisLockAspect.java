package com.ziyan.springbootredisson.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author ziyan
 * @email zhengmengyan@taoqicar.com
 * @date 2018年06月05日
 */
@Aspect
public class RedisLockAspect {
    @Around("execution(* com.ziyan.springbootredisson..*)&& @annotation(com.ziyan.springbootredisson.annotation.RedisLock)")
    public Object lock(ProceedingJoinPoint point) throws Throwable {
        return null;
    }
}
