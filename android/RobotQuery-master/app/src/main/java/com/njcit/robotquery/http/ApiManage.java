package com.njcit.robotquery.http;

import android.util.Log;

import com.njcit.robotquery.base.BaseApplication;
import com.njcit.robotquery.http.api.chatApi;
import com.njcit.robotquery.http.api.queryApi;
import com.njcit.robotquery.http.api.templateApi;
import com.njcit.robotquery.http.api.zhihuApi;
import com.njcit.robotquery.util.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by youxuan on 2017/3/18 0018.
 */

public class ApiManage {


    private chatApi mChatApi;
    private queryApi mQueryApi;
    private templateApi mUpdateModelApi;
    private Object zhihuMonitor = new Object();

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetWorkUtil.isNetWorkAvailable(BaseApplication.getInstance())) {
                int maxAge = 60;  //在线缓存一分钟可读取

                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28;//离线时缓存4周

                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    public static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(
            new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.d("HttpLog", "OkHttp====message " + message);
                }

            });


    public static ApiManage apiManage;

    private static File httpCacheDiretory = new File(BaseApplication.getInstance().getCacheDir(), "robotquery");
    private static int cacheSize = 10 * 1024 * 1024;
    private static Cache cache = new Cache(httpCacheDiretory, cacheSize);
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
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


    public chatApi getChatService() {
        if (mChatApi == null) {
            synchronized (zhihuMonitor) {
                if (mChatApi == null) {
                    mChatApi = new Retrofit.Builder()
                            .baseUrl(HttpUrlBase.CHAT_URL_BASE)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(chatApi.class);
                }
            }
        }

        return mChatApi;
    }

    public queryApi getQueryService(){
        if (mQueryApi == null){
            synchronized (zhihuMonitor){
                if (mQueryApi == null){
                    mQueryApi = new Retrofit.Builder()
                            .baseUrl(HttpUrlBase.CHAT_URL_BASE)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(queryApi.class);
                }
            }
        }

        return mQueryApi;
    }

    public templateApi getUpdatetModelService(){
        if (mUpdateModelApi == null){
            synchronized (zhihuMonitor){
                if (mUpdateModelApi == null){
                    mUpdateModelApi = new Retrofit.Builder()
                            .baseUrl(HttpUrlBase.CHAT_URL_BASE)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(templateApi.class);
                }
            }
        }

        return mUpdateModelApi;
    }
}
