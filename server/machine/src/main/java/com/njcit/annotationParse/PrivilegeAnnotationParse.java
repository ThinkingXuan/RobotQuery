//package com.njcit.annotationParse;
//
//import com.njcit.annotation.PrivilegeInfo;
//import com.njcit.model.Message;
//import com.njcit.model.User;
//
//import java.lang.reflect.Method;
//
///**
// * Created by mirko on 2017/3/20.
// */
//public class PrivilegeAnnotationParse {
////    targetClass 目标类的class形式
////    methodName 在客户端调用哪个方法，methodNAme就代表哪个方法
//    public static String parse(Class targetClass,String methodName) throws Exception{
//
//        System.out.println("------------正在对PrivilegeInfo注解进行解析------------");
//        String methodAccess = "";
//            Method method = targetClass.getMethod(methodName,User.class);
//                if(method.isAnnotationPresent(PrivilegeInfo.class)){
//                    PrivilegeInfo privilegeInfo = method.getAnnotation(PrivilegeInfo.class);
//                        methodAccess = privilegeInfo.value();
//                }
//        System.out.println("解析成功,值为：");
//        System.out.println(methodAccess);
//        return methodAccess;
//    }
//}

