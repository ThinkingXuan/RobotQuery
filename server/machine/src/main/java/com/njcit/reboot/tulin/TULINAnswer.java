package com.njcit.reboot.tulin;
import com.njcit.reboot.tulin.api.ApiManage;
import com.njcit.reboot.tulin.bean.question.BaseBean;
import com.njcit.utils.GsonUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import java.io.IOException;
/**
 * Created by mirko on 2017/3/31.
 */
public class TULINAnswer {

    //判断返回数据类型
//    public  static String Judge(String json){
//        JSONObject jsonObject = new JSONObject(json);
//        return jsonObject.getString("code");
//    }

    //获得图灵返回的结果
    public static String getAnswer(String question,String userId) throws IOException {
        BaseBean baseBean = new BaseBean();
        baseBean.setInfo(question);
        baseBean.setUserid(userId);
        String jsonQuestion = GsonUtil.ObjectToJson(baseBean,baseBean.getClass());
        Call<ResponseBody> call = ApiManage.getInstence().getChatService().getMessage(getRequestBody(jsonQuestion));
        String APIAnswer = call.execute().body().string();
        return APIAnswer;
    }
//    获得请求体 开发者无需关心
    public static RequestBody getRequestBody(String jsonQuestion) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"),jsonQuestion);
        if (body != null) {
            return body;
        }
        return null;
    }




}
