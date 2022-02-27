package com.njcit.robotquery.moudle.chat.fixtures;


import com.njcit.robotquery.moudle.chat.models.DefaultUser;
import com.njcit.robotquery.view.chatUI.commons.models.IMessage;
import com.njcit.robotquery.view.chatUI.commons.models.IUser;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/*
 * Created by troy379 on 12.12.16.
 */
public final class MessagesListFixtures extends FixturesData {
    private MessagesListFixtures() {
        throw new AssertionError();
    }

    public static ArrayList<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message message = new MessagesListFixtures.Message();

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            message.createdAt = calendar.getTime();

            messages.add(message);
        }
        return messages;
    }

    public static class Message implements IMessage,Serializable {

        private long id;
        private String text;                //副文本
        private String MainText;            //主文本
        private String objType;             //对象类型
        private Object obj;                 //对象
        private Date createdAt;
        private String url;
        private int isRobot;                //0自己,1机器人

        public Message() {

        }

        public Message(String text) {
            this.text = text;
            this.id = UUID.randomUUID().getLeastSignificantBits();
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        @Override
        public Date getCreatedAt() {
            return createdAt == null ? new Date() : createdAt;
        }

        @Override
        public String getUrl() {
            return url;
        }



        public void setText(String text) {
            this.text = text;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String getId() {
            return String.valueOf(id);
        }

        public int getIsRobot() {
            return isRobot;
        }

        public void setIsRobot(int isRobot) {
            this.isRobot = isRobot;
        }

        //加载一个默认用户,这个用户拥有一些属性。比如头像
        @Override
        public IUser getUser() {
//            return new DefaultUser(isRobot % 2 == 0 ? "1" : "0", isRobot % 2 == 0 ? names.get(0) : names.get(1),
//                    isRobot % 2 == 0 ? avatars.get(0) : avatars.get(1), true);
            return new DefaultUser(isRobot+"", isRobot % 2 == 0 ? names.get(0) : names.get(1),
                    isRobot % 2 == 0 ? avatars.get(0) : avatars.get(1), true);
        }

        @Override
        public String getText() {
            return text;
        }

        public String getStatus() {
            return "Sent";
        }

        public String getMainText() {
            return MainText;
        }

        public void setMainText(String mainText) {
            MainText = mainText;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }

        public String getObjType() {
            return objType;
        }

        public void setObjType(String objType) {
            this.objType = objType;
        }

    }
}
