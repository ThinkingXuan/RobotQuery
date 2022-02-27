package com.njcit.robotquery.data;

import android.content.Context;
import android.util.Log;

import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.http.cache.ACache;
import com.njcit.robotquery.moudle.chat.fixtures.MessagesListFixtures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by youxuan on 2017/4/2 0002.
 * 处理数据缓存(根据你传每页传入的个数,和要缓存页数,进行动态缓存。缓存最近的聊天记录)
 * 默认缓存 per_page * 2 条数据
 */
public class DataCache {
    public static final String TAG = "DataCache";
    private int page;
    private int countPage;
    private int per_page;
    private ACache mACache;
    private int cache_count;
    private List<MessagesListFixtures.Message> mCacheList;
    private int timeCache = 86400;  //24小时

    public DataCache(Context mContext){
        mACache = ACache.get(mContext);
        page = 1;                       //默认缓存第一页,10条数量
        per_page = 10;
        cache_count = per_page *2;      //默认缓存 per_page * 2 条数据
        if (null!=getCache()) {
            mCacheList = getCache();               //获取先前的缓存
        }else {
            mCacheList = new ArrayList<>();
        }
    }

    public DataCache(Context mContext,int page,int per_pager){
        mACache = ACache.get(mContext);
        this.page = page;              //自定义缓存的页数和每页数量
        this.per_page = per_pager;
        cache_count = per_page *2;      //默认缓存 per_page * 2 条数据
        mCacheList = new ArrayList<>();
    }

    public int getPage() {
        return mCacheList.size()/per_page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }

    //已集合的形式缓存
    public void addCache(List<MessagesListFixtures.Message> datas){
        int dataCount =  datas.size();

        if (dataCount>0 && dataCount <= getCache_count()) {
            mCacheList = datas;
        }else if (dataCount>0 && dataCount >getCache_count()){
            //获取最后的cache_count条数据
            mCacheList = getLastCountDatas(datas);
        }
    }

    //单个数量缓存
    public void addCache(MessagesListFixtures.Message data){
        if (null != data){

            Log.d(TAG, "addCache: "+data.toString());
            if (mCacheList.size()< getCache_count()){
                Log.d(TAG, "addCache: ");
                mCacheList.add(data);
            }else {
                Log.d(TAG, "noAddCache: ");
                mCacheList.remove(0);
                mCacheList.add(data);
                Log.d(TAG, "addCache: ");
            }
        }
    }

    //根据你传入的页数获取缓存
    public List<MessagesListFixtures.Message> getCache(int page){
        return null;
    }

    //获取全部缓存,需要判断已缓存的多少页
    public List<MessagesListFixtures.Message> getCache(){

        List<MessagesListFixtures.Message>  datas = (List<MessagesListFixtures.Message>) mACache.getAsObject(Constants.CHAT_DATA);
        if (null != datas && datas.size()>0){
            return datas;
        }else {
            return null;
        }
    }

    //提交缓存
    public  void commit(){
        mACache.put(Constants.CHAT_DATA, (Serializable) mCacheList,timeCache); //缓存5分钟
    }


    //循环遍历,效果不太好呀！---------------------------------------------
    private List<MessagesListFixtures.Message> getLastCountDatas(List<MessagesListFixtures.Message> datas){
        int endIndex = datas.size() - 1;
        int startIndex = datas.size() -1 - getCache_count();
        List<MessagesListFixtures.Message> newsList = new ArrayList<>();

        for (int i = startIndex; i <= endIndex ; i++) {
            newsList.add(datas.get(i));
        }

        return newsList;
    }

    private int getCache_count(){
        return cache_count;
    }
}
