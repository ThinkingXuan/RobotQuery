//package com.njcit.aspect;
//import com.njcit.annotationParse.PrivilegeAnnotationParse;
//import com.njcit.model.FirmPrivilege;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ResponseBody;
//import java.util.ArrayList;
//import java.util.List;
///**
// * Created by mirko on 2017/3/20.
// */
//@Component
//@Aspect
//public class PrivilegeAspect {
//    private final org.slf4j.Logger logger = LoggerFactory.getLogger(LogAspect.class);
//
//    @Pointcut(value ="execution(* com.njcit.controller.*.*(..))")
//    public void PointCut(){
//    }
//
//
//    @Around("PointCut()")
//    @ResponseBody
//    public String  isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        List<FirmPrivilege>  privileges = new ArrayList<FirmPrivilege>();
//        privileges.add(new FirmPrivilege("login"));
//        privileges.add(new FirmPrivilege("save"));
//
//    Class targetClass = joinPoint.getTarget().getClass();
//    String methodName = joinPoint.getSignature().getName();
//
//    String methodAccess = PrivilegeAnnotationParse.parse(targetClass,methodName);
//
//    boolean isAccessed = false;
//    for(FirmPrivilege firmPrivilege : privileges){
//        if("".equals(methodAccess)){
//            isAccessed = true;
//            break;
//        }
//        if(firmPrivilege.getValue()!=null&&firmPrivilege.getValue().equalsIgnoreCase(methodAccess)){
//            isAccessed = true;
//            break;
//        }
//    }
//    if(isAccessed){
//        System.out.println("拥有权限，正在保存");
//       String json = (String) joinPoint.proceed();
//       return json;
//    }else {
//        //return GetStatusUtil.getStatusByJson(ResultStatus.NO_ACCESS);
//    }
//    return null;
//
//    }
//
//
//
//
//}
