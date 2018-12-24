package com.example.demo.logging;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.core.env.Environment;


/**
 * Aspect for logging execution of service and repository Spring components.
 * Runs with 'dev" profile by default
 */
public class LoggingAspect {
	
	private static final Logger log = Logger.getLogger(LoggingAspect.class);
	
	private final Environment env;
	
	public LoggingAspect(Environment env) {
		this.env = env;
	}

	/**
	 * Pointcut that matches all repositories, services and Web REST endpoints.
	 */
    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }
    
    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.example.demo.repository..*)"+
            " || within(com.example.demo.service..*)"+
            " || within(com.example.demo.web.rest..*)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Advice log methods throwing exceptions
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        if (env.acceptsProfiles("dev")) {
            log.error("Exception with cause = ", joinPoint.getSignature(), e);
        }
    }
    
    /**
     * Advice that logs when a method is entered and exited.
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: with argument[s]", Arrays.toString(joinPoint.getArgs()), new Throwable());
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: with result = ", result, new Throwable());
            }
            return result;
        } catch (IllegalArgumentException excep) {
            log.error("Illegal argument in", Arrays.toString(joinPoint.getArgs()), excep);

            throw excep;
        }
    }


}
