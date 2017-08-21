package sprittr.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import sprittr.config.Response;

/**
 * ResponseAop
 *
 * @author zf
 * @date 16/6/30
 */
@Aspect
@Configuration
public class ResponseAop {

    @Pointcut("execution(* sprittr.webd.*.*(..))") // expression
    private void responsePointCut() {}  // signature

    @Around(value = "responsePointCut()")
    public Object addResponseResult(ProceedingJoinPoint point) throws Throwable {
        return new Response().success(point.proceed());
    }
}
