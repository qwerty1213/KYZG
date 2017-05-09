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
}
