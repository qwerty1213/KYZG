package test.bwie.com.example.ins7566.kyzg.http;

import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by INS7566 on 2017/5/9.
 */

public interface INewsModel {
    //新闻
    void news(String catalog, String pageIndex, String pageSize, MyCallback callback);
    //咨询详情
    void NewsDetail(String id,MyCallback callback);
    //博客
    void Blog(String type,String pageIndex,String pageSize,MyCallback callback);
    //博客详情
    void BlogDetail(String id,MyCallback callback);
    //热点
    void HotSpot(String catalog,String pageIndex,String pageSize,String show,MyCallback  callback);
     //推荐
    void Recommend(String type,String pageIndex,String pageSize,MyCallback callback);
    //最新动弹
    void NewestTweent(String uid,String pageIndex,String pageSize,MyCallback callback);
    //热门动弹
    void HotTweent(String uid,String pageIndex,String pageSize,MyCallback callback);
    //我的动弹
    void MineTweent(String uid,String pageIndex,String pageSize,MyCallback callback);
    //轮播图
    void ipanda( MyCallback callback);
    //搜索
    void getSearch(String catalog,String content,String pageIndex,String pageSize,MyCallback callback);
    //搜索找人
    void SerachPeople(String name,MyCallback callback);
}
