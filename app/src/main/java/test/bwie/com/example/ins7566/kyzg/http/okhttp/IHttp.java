package test.bwie.com.example.ins7566.kyzg.http.okhttp;

import java.util.Map;

import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

/**
 * Created by INS7566 on 2017/5/8.
 */

public interface IHttp {
    //请求数据用的方法
    void Post(String url, Map<String, String> params, MyCallback callback);
    void Get(String url, Map<String, String> params, MyCallback callback);
}
