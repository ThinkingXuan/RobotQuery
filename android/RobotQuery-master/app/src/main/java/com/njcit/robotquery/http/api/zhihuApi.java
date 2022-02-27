package com.njcit.robotquery.http.api;

import com.njcit.robotquery.bean.ZhihuDaily;
import com.njcit.robotquery.bean.ZhihuStory;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by youxuan on 2017/3/18 0018.
 */

public interface zhihuApi {

    @GET("/api/4/news/latest")
    Observable<ZhihuDaily> getLashDaily();

    @GET("/api/4/news/before/{data}")
    Observable<ZhihuDaily> getTheDaily(@Path("data") String data);

    @GET("/api/4/news/{id}")
    Observable<ZhihuStory> getZhihuStory(@Path("id") String id);


}
