package test.bwie.com.example.ins7566.kyzg.http;

import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

/**
 * Created by INS7566 on 2017/5/9.
 */

public interface INewsModel {
    //新闻
    void news(String catalog, String pageIndex, String pageSize, MyCallback callback);
    //咨询详情
    void NewsDetail(String id,MyCallback callback);
    //博客

}
