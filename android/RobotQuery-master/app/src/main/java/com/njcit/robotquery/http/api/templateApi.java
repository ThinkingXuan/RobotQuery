package com.njcit.robotquery.http.api;

import com.njcit.robotquery.bean.server.BaseBean;
import com.njcit.robotquery.bean.server.TemplateTagBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by youxuan on 2017/4/10 0010.
 */

public interface templateApi {

    @FormUrlEncoded
    @POST("/system/getTemplateTag.action")
    Observable<TemplateTagBean> getTemplateTag(@Field("userId") String userId);

    /**
     * 上传模板
     *
     * @param body json
     * @return
     */

    @POST("/user/insertNewModel.action")
    Observable<BaseBean> updateModel(@Body RequestBody body);

}
