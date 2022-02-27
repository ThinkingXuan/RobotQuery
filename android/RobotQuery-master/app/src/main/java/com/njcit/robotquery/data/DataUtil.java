package com.njcit.robotquery.data;

import android.util.Log;


import com.njcit.robotquery.bean.server.ChatMessage;
import com.njcit.robotquery.bean.tuling.News;
import com.njcit.robotquery.bean.tuling.Photo;
import com.njcit.robotquery.bean.tuling.Recipes;
import com.njcit.robotquery.bean.tuling.Text;
import com.njcit.robotquery.moudle.chat.fixtures.MessagesListFixtures;
import com.njcit.robotquery.util.DateUtil;
import com.njcit.robotquery.util.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by youxuan on 2017/3/25 0025.
 */

public class DataUtil {

    public static final String TAG = "DataUtil";

    //把服务器请求的数据,转换为视图中的数据。(主要是处理Time,Type,IsRobot,并且根据时间进行排序)
    public static List<MessagesListFixtures.Message> getMessage(ChatMessage chatMessage) {
        List<ChatMessage.ObjectBean> objectBeen = chatMessage.getObject();

        //排序
        Collections.sort(objectBeen, new Comparator<ChatMessage.ObjectBean>() {
            @Override
            public int compare(ChatMessage.ObjectBean o1, ChatMessage.ObjectBean o2) {
                return (int) DateUtil.getDateStrToLong(o1.getTime()) - (int) DateUtil.getDateStrToLong(o2.getTime());
            }
        });

        List<MessagesListFixtures.Message> messageslist = new ArrayList<MessagesListFixtures.Message>();
        for (ChatMessage.ObjectBean m : objectBeen) {
            MessagesListFixtures.Message message = getObjectBean(m);

            message.setIsRobot(Integer.valueOf(m.getIsRobot()));
            message.setCreatedAt(DateUtil.getDateTime(m.getTime()));

            messageslist.add(message);
        }

        return messageslist;
    }

    /**
     * 服务器数据类型转换器,单个数据。及时聊天使用。
     *
     * @param chatMessage
     * @return
     */
    public static MessagesListFixtures.Message messageConveter(ChatMessage chatMessage) {
        ChatMessage.ObjectBean list = chatMessage.getObject().get(0);
        return getObjectBean(list);
    }

    //把ObjectBean转为Message
    public static MessagesListFixtures.Message getObjectBean(ChatMessage.ObjectBean objectBean) {

        MessagesListFixtures.Message message;

        if (objectBean.getType().equals("text")) {       //本地text类型
            message = new MessagesListFixtures.Message(objectBean.getContent());
            message.setIsRobot(1);
            return message;
        } else if (objectBean.getType().equals("json")) {//图灵的json类型

            String json = objectBean.getContent();        //获取里面的内容
            String code = getCode(json);           //获取消息的类型
            //普通文字  100000

            if (code.equals("100000")) {
                Text textList = JsonUtil.getText(json);

                Log.d(TAG, "messageConveter: " + textList.toString());
                message = new MessagesListFixtures.Message(textList.getText());
                message.setIsRobot(1);
                return message;
            }

            //图片 200000
            if (code.equals("200000")) {
                Log.d(TAG, "messageConveter: code:" + code);
                Photo photo = JsonUtil.getPhoto(json);
                Log.d(TAG, "messageConveter: photo:" + photo.toString());

                message = new MessagesListFixtures.Message(photo.getText() + "<br/><a href=\"" + photo.getUrl() + "\">" + "查看图片" + "</a>");

                message.setIsRobot(1);


                return message;
            }

            //菜谱  308000
            if (code.equals("308000")) {
                Recipes recipe = JsonUtil.getReciper(json);
                Log.d(TAG, "messageConveter: " + recipe);
                List<Recipes.ListBean> listbean = recipe.getList();

                String textStr = "";
                for (Recipes.ListBean b : listbean) {

                    textStr += "<br/>" + b.getName() + ":<br/>" + b.getInfo() + "<br/>" + "<a href=\"" + b.getDetailurl() + "\">" + "点击查看详情" + "</a><br/>";
                }

                message = new MessagesListFixtures.Message(recipe.getText() + textStr);

                message.setIsRobot(1);

                return message;
            }

            // 新闻 302000
            if (code.equals("302000")) {
                News news = JsonUtil.getnews(json);

                List<News.ListBean> listbean = news.getList();
                String textStr = "";
                for (News.ListBean b : listbean) {

                    textStr += "<br/>标题:" + b.getArticle() + ":<br/>来源:" + b.getSource() + "<br/>" + "<a href=\"" + b.getDetailurl() + "\">" + "点击查看详情" + "</a><br/>";
                }

                message = new MessagesListFixtures.Message(news.getText() + textStr);
                message.setIsRobot(1);

                return message;
            }
        }
        return null;
    }


    //这样效率又低了，但是没办法呀。
    public static String getCode(String json) {

        JSONObject jsonObject = null;
        String code = "";
        try {
            jsonObject = new JSONObject(json);
            code = jsonObject.getInt("code") + "";

            return code;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";

    }


    /**
     * 拼接sql语句的查询条件(不带范围)
     *
     * @param name
     * @param vlaue
     * @return
     */
    public static String getMapValue(String name, String vlaue) {
        if (!name.equals("")) {
            return name + "=" + "'" + vlaue + "'";
        }
        return "";
    }

    /**
     * 拼接sql语句的查询条件(带范围)
     */
    public static String getMapValue(String name, String value1, String value2) {
        if (!name.equals("")) {
            return name + ">=" + value1 + " and " + name + "<=" + value2;
        }

        return "";
    }


    //过滤字符串只保留数字、中文字符和
    public static String filiteString(String s) {


        if (s != null) {

            String regEx2 = "[^(0-9\\u4e00-\\u9fa5)]";

            Pattern p = Pattern.compile(regEx2);
            Matcher m = p.matcher(s);
            return m.replaceAll("");
        }
        return "";
    }

    /**
     * 把数字字符从字符串中过滤出来。
     * sfd454**^&^&^34545       454  34545
     * @param s
     * @return
     */
    public static String filiterStringGroup(String s){

        String value1 = "";
        String value2 = "";
        if (s!=null){
            String regEx = "(\\d+)[^\\d]+(\\d+)";
            Pattern pattern = Pattern.compile(regEx);
            Matcher m = pattern.matcher(s);

            if (m.find()){
                value1 = m.group(1);
                value2 = m.group(2);
            }

        }

        return value1+" - "+value2;
    }



    public static String getProductTypeId(String typeName){
        String typeId = "1";
        switch (typeName){
            case "饮料":
                typeId = "1";
                break;
            case "调味瓶":
                typeId = "2";
                break;
            case "点心":
                typeId = "3";
                break;
            case "日用品":
                typeId = "4";
                break;
            case "谷类/麦片":
                typeId = "5";
                break;
            case "肉/家禽":
                typeId = "6";
                break;
            case "特制品":
                typeId = "7";
                break;
            case "海鲜":
                typeId = "8";
                break;
        }

        return typeId;
    }
    public static String getProductTypeName(String typeId){
        String typeName = "饮料";
        switch (typeId){
            case "1":
                typeName = "饮料";
                break;
            case "2":
                typeName = "调味瓶";
                break;
            case "3":
                typeName = "点心";
                break;
            case "4":
                typeName = "日用品";
                break;
            case "5":
                typeName = "谷类/麦片";
                break;
            case "6":
                typeName = "肉/家禽";
                break;
            case "7":
                typeId = "特制品";
                break;
            case "8":
                typeName = "海鲜";
                break;
        }

        return typeName;
    }
}
