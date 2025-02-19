package com.example.BitlyCloneApplication.AOP;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
@EnableAspectJAutoProxy
public class AopConfiguration {
    Logger logger = LoggerFactory.getLogger(AopConfiguration.class);
    @Pointcut("execution(* com.example.BitlyCloneApplication.controller.*.loginUser(..))")
    public void authenticationPointCut() {
        logger.info("LOGIN USER POINT CUT");
    }

    @Around("authenticationPointCut()")
    public Object application(ProceedingJoinPoint pjp) throws Throwable {
    //    ObjectMapper mapper = new ObjectMapper();
    //    mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    //    mapper.configure(MapperFeature.USE_ANNOTATIONS, false);
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().getSimpleName();

        // Filter out BindingResult
        Object[] filteredArgs = Arrays.stream(pjp.getArgs())
                .filter(arg -> !(arg instanceof org.springframework.validation.BindingResult))
                .toArray();
       logger.info("METHOD INVOKED {}:{}() arguments: {}",className,methodName,filteredArgs);
        Object result = pjp.proceed();
       logger.info("METHOD RETURNED {}:{}() response: {}",className,methodName,result);
        return result;
    }

}







//centralized logging
//profiling
//security
