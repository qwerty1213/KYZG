package test.bwie.com.example.ins7566.kyzg.http;

import java.util.HashMap;
import java.util.Map;

import test.bwie.com.example.ins7566.kyzg.http.UrlUtils.UrlUtils;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;
import test.bwie.com.example.ins7566.kyzg.http.okhttp.HttpFactory;

/**
 * Created by INS7566 on 2017/5/9.
 */

public class NewsModelImpl implements INewsModel {
    @Override
    public void news(String catalog, String pageIndex, String pageSize, MyCallback callback) {
        Map<String,String> params =new HashMap<>();
        params.put("catalog",catalog);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",pageSize);
        HttpFactory.create().Get(UrlUtils.getNews,params,callback);
    }

    @Override
    public void NewsDetail(String id, MyCallback callback) {
        Map<String ,String> map=new HashMap<>();
        map.put("id",id);
        HttpFactory.create().Get(UrlUtils.getDetail,map,callback);
    }

    @Override
    public void Blog(String type, String pageIndex, String pageSize, MyCallback callback) {
        Map<String,String> params=new HashMap<>();
        params.put("type",type);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",pageSize);
        HttpFactory.create().Get(UrlUtils.getBlog,params,callback);
    }

    @Override
    public void BlogDetail(String id, MyCallback callback) {
        Map<String,String> map=new HashMap<>();
        map.put("id",id);
        HttpFactory.create().Get(UrlUtils.getBlogDetail,map,callback);
    }

    @Override
    public void HotSpot(String catalog, String pageIndex, String pageSize, String show, MyCallback callback) {
        Map<String,String> params=new HashMap<>();
        params.put("catalog",catalog);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",pageSize);
        params.put("show",show);
        HttpFactory.create().Get(UrlUtils.getRedian,params,callback);
    }

    @Override
    public void Recommend(String type, String pageIndex, String pageSize, MyCallback callback) {
        Map<String,String> params=new HashMap<>();
        params.put("type",type);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",pageSize);
        HttpFactory.create().Get(UrlUtils.getBlog,params,callback);
    }

    @Override
    public void NewestTweent(String uid, String pageIndex, String pageSize, MyCallback callback) {
        Map<String,String> params=new HashMap<>();
        params.put("uid",uid);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",pageSize);
        HttpFactory.create().Get(UrlUtils.getNewDT,params,callback);
    }

    @Override
    public void HotTweent(String uid, String pageIndex, String pageSize, MyCallback callback) {
        Map<String,String> params=new HashMap<>();
        params.put("uid",uid);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",pageSize);
        HttpFactory.create().Get(UrlUtils.ReMenDT,params,callback);

    }

    @Override
    public void MineTweent(String uid, String pageIndex, String pageSize, MyCallback callback) {
        Map<String,String> params=new HashMap<>();
        params.put("uid",uid);
        params.put("pageIndex",pageIndex);
        params.put("pageSize",pageSize);
        HttpFactory.create().Get(UrlUtils.mineTweet,params,callback);

    }

    @Override
    public void ipanda(MyCallback callback) {
        Map<String,String> map=new HashMap<>();
        HttpFactory.create().Get(UrlUtils.ipanda,map,callback);
    }
}
