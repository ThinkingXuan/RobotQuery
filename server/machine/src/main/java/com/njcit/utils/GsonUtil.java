package com.njcit.utils;

import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;



public class GsonUtil {

	public static String ObjectToJson(Object object,Type type){

		 Gson gson=new Gson();
		return gson.toJson(object,type);
	}

	public static String ObjectToJson(Object object){

		Gson gson=new Gson();

		return gson.toJson(object);
	}


	public static Object JsonToObject(String json,Type type){

		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}

	public static  String ListToJson(List<?> list){

		Gson gson = new Gson();
		return gson.toJson(list);
	}



}
