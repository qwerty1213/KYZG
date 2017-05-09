package test.bwie.com.example.ins7566.kyzg.http;

import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

/**
 * Created by INS7566 on 2017/5/9.
 */

public interface INewsModel {
    //新闻列表的调用
    void news(String catalog, String pageIndex, String pageSize, MyCallback callback);
}
