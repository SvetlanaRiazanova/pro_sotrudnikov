package di.aittr.pro_sotrudnikov.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogging {

    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);

    @Pointcut("execution(* *..*Service*.*(..))")
    public void allServiceMethods() {
    }

    @Before("allServiceMethods()")
    public void peredMetodom(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Метод {} из класса {} вызван с аргументом: {}", methodName, className, args);

    }

    @AfterReturning(
            pointcut = "allServiceMethods()",
            returning = "rezultat")
    public void posleVozvracheniya(JoinPoint joinPoint, Object rezultat) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("Метод {} из класса {} успешно вернул: {}", methodName, className, rezultat);

    }

    @AfterThrowing(
            pointcut = "allServiceMethods()",
            throwing = "e"
    )
    public void vibrasivaetOschibku(JoinPoint joinPoint, Exception e){
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.warn("Метод {} из класса {} выбросил ошибку: {}", methodName, className, e.getMessage());

    }
}














































































