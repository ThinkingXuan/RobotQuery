package com.njcit.design;

/**
 * Created by mirko on 2017/4/18.
 */
public interface GetConfig extends IConfig{

        Object getArg(String lable,Object value);//根据某个值查询
        Object getArgs(String[] label,Object[] value);//组合查询
        Object getArgMore(String label,Object value);//大于查询
        Object getArgLess(String label,Object value);//小于查询
        Object getArgBetween(String label,Object value1,Object value2);//中间查询


}
