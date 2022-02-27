package com.njcit.robotquery.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.njcit.robotquery.bean.server.Employee;
import com.njcit.robotquery.bean.server.Model;
import com.njcit.robotquery.bean.tuling.News;
import com.njcit.robotquery.bean.tuling.Photo;
import com.njcit.robotquery.bean.tuling.Recipes;
import com.njcit.robotquery.bean.tuling.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youxuan on 2017/4/1 0001.
 * Json解析
 */

public class JsonUtil {


    public static <T> List<T> getObject(Class<T> cls, String json) {
        List<T> list = new ArrayList<>();
        Gson gson = new Gson();
        list = gson.fromJson(json, new TypeToken<List<T>>() {
        }
                .getType());

        if (list.size() > 0 && null != list) {
            return list;
        }

        return null;
    }

    public static Recipes getReciper(String json) {

        Gson gson = new Gson();
        Recipes recipes = gson.fromJson(json, new TypeToken<Recipes>() {
        }
                .getType());

        if (null != recipes) {
            return recipes;
        }

        return null;
    }


    public static News getnews(String json) {

        Gson gson = new Gson();
        News news = gson.fromJson(json, new TypeToken<News>() {
        }
                .getType());

        if (null != news) {
            return news;
        }

        return null;
    }

    public static Text getText(String json) {

        Gson gson = new Gson();
        Text text = gson.fromJson(json, new TypeToken<Text>() {
        }
                .getType());

        if (null != text) {
            return text;
        }

        return null;
    }

    public static Photo getPhoto(String json) {

        Gson gson = new Gson();
        Photo photo = gson.fromJson(json, new TypeToken<Photo>() {
        }
                .getType());

        if (null != photo) {
            return photo;
        }

        return null;
    }

    public static String getModelJson(Model model) {
        Gson gson = new Gson();

        if (model != null) {
            return gson.toJson(model);
        }
        return "";
    }


    public static <T> String getObjectJson(T t) {
        //避免Gson使用时将一些字符自动转换为Unicode转义字符
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        if (t != null) {
            return gson.toJson(t);
        }
        return "";
    }
}
