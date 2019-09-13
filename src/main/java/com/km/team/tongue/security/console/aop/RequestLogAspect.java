package com.km.team.tongue.security.console.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.km.team.tongue.security.console.beans.vo.ResponseData;
import com.km.team.tongue.security.console.utils.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: jiangjun
 * Date: 2018/9/21
 * Time: 上午11:27
 * Description:
 */
@Component
@Aspect
public class RequestLogAspect {

    private final Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);

    // 定义切点Pointcut
    @Pointcut("execution(* com.km.team.phsystem.*.controller..*(..))")
    public void requestService() {
    }

    @Around("requestService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = sra.getRequest();
        // 获取请求uri、方法、类名等信息
        //String remoteAddress = request.getRemoteHost() + ":" + request.getRemotePort();
        String remoteAddress = IPUtils.getProxyIP(request);
        String uri = request.getRequestURI();
        String methodName = pjp.getSignature().getName();
        String clazzName = pjp.getTarget().getClass().getSimpleName();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 获取参数名称，参数值
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] parameterValues = pjp.getArgs();
        StringBuilder paramBuilder = new StringBuilder();
        if (parameterNames != null && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                if (parameterNames[i].equals("bindingResult")) {
                    break;
                }
                if ((parameterValues[i] instanceof HttpServletRequest) || (parameterValues[i] instanceof HttpServletResponse)) {
                    paramBuilder.
                            append("[").
                            append(parameterNames[i]).append("=").append(parameterValues[i])
                            .append("]");
                } else {
                    paramBuilder.
                            append("[").
                            append(parameterNames[i]).append("=")
                            .append(JSON.toJSONString(parameterValues[i], SerializerFeature.WriteDateUseDateFormat))
                            .append("]");
                }
            }
        }
        // 序列化
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            logger.error("request error in aspect! request ip info: 【{}】 ^_^ request url: 【{}】 ^_^ request Controller: 【{}】 ^_^ " +
                            "request method: 【{}】 ^_^ request param: {}",
                    remoteAddress, uri, clazzName, methodName, paramBuilder.toString());
            throw throwable;
        }
        String jsonString = "";
        if (result != null) {
            if (result instanceof ResponseData) {
                jsonString = JSON.toJSONString(result, SerializerFeature.WriteDateUseDateFormat);
            } else {
                jsonString = String.valueOf(result);
            }
        }
        long endTime = System.currentTimeMillis();
        long usedTime = endTime - startTime;
        if (uri.contains("upload")) {
            logger.info("request complete! request used time: 【{}ms】 ^_^ request ip info: 【{}】 ^_^ request url: 【{}】 ^_^ request Controller: 【{}】 ^_^ " +
                            "request method: 【{}】 ^_^ response data: 【{}】",
                    usedTime, remoteAddress, uri, clazzName, methodName, jsonString);
        }else{
            logger.info("request complete! request used time: 【{}ms】 ^_^ request ip info: 【{}】 ^_^ request url: 【{}】 ^_^ request Controller: 【{}】 ^_^ " +
                            "request method: 【{}】 ^_^ request param: 【{}】 ^_^ response data: 【{}】",
                    usedTime, remoteAddress, uri, clazzName, methodName, paramBuilder.toString(), jsonString);
        }
        return result;
    }
}
