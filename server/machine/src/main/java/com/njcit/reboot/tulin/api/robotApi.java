package com.njcit.reboot.tulin.api;


import okhttp3.RequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface robotApi{

    //发送Post请求,请求数据为Json
    @POST("/openapi/api")
    Call<okhttp3.ResponseBody> getMessage(@Body RequestBody body);

}
