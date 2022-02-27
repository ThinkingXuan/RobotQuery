package com.njcit.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
     * Created by mirko on 2017/3/18.
     */
    @Component
    @Aspect
    public class LogAspect {
        private final org.slf4j.Logger logger = LoggerFactory.getLogger(LogAspect.class);
        /*
            切入点
         */
        @Pointcut(value ="execution(* com.njcit.controller.*.*(..))")
        public void  pointCut(){
        }

        @Before(value ="pointCut()")
        public void before(JoinPoint joinPoint){

            logger.info("-------------------正在记录日志-------------------");

        }

        @AfterThrowing(value = "pointCut()",throwing ="ex")
        public void Exception(JoinPoint joinPoint,Throwable ex){

            logger.error("---------------出现异常:"+"ex.getMessage()+"+"----------");
        }

        @After(value ="pointCut()")
        public void after(){

            logger.info("----------------------日志记录结束------------------");

        }



}
