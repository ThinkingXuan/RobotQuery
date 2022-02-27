package com.njcit.reboot.tulin.api;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by youxuan on 2017/3/31 0031.
 */
public class ApiManage {

    private Object zhihuMonitor = new Object();
    private robotApi mRobotApi;
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
                int maxAge = 60;  //在线缓存一分钟可读取
                return  originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
        }
    };
    public static ApiManage apiManage;
    private static File httpCacheDiretory = new File("c:\\cache","robotquery");
    private static int cacheSize = 10 * 1024 *1024;
    private static Cache cache = new Cache(httpCacheDiretory,cacheSize);
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            //.addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .cache(cache)
            .build();

    public static ApiManage getInstence() {

        if (apiManage == null) {
            synchronized (ApiManage.class) {
                if (apiManage == null) {
                    apiManage = new ApiManage();
                }
            }
        }
        return apiManage;
    }
    public robotApi getChatService(){

        if (mRobotApi==null){
            synchronized (zhihuMonitor){
                if (mRobotApi ==null){
                    mRobotApi = new Retrofit.Builder()
                            .baseUrl("http://www.tuling123.com")
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(robotApi.class);
                }
            }
        }

        return mRobotApi;
    }

}
