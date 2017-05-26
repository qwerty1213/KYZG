package test.bwie.com.example.ins7566.kyzg.http;

import android.content.SharedPreferences;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import test.bwie.com.example.ins7566.kyzg.App;
import test.bwie.com.example.ins7566.kyzg.http.IMineModel;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.UrlUtils.UrlUtils;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;
import test.bwie.com.example.ins7566.kyzg.http.okhttp.HttpFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by INS7566 on 2017/5/11.
 */

public class MineModelImpl implements IMineModel {
    private String cookie;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    @Override
    public void Login(String username, String pwd, String keep_login,final MyCallback callback) {
        Map<String,String> params=new HashMap<>();
        params.put("username",username);
        params.put("pwd",pwd);
        params.put("keep_login",keep_login);
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder body=new FormBody.Builder();
        if (params!=null){
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                String value = params.get(key);
                body.add(key,value);
            }



        }
        Request request=new Request.Builder().url(UrlUtils.Login).post(body.build()).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, IOException e) {
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError("失败");
                    }
                });
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                saveCookie(response);
                final String s = response.body().string();
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(s);
                    }
                });
            }
        });

    }

    @Override
    public void getFensi(String uid, String relation, String pageIndex, String pageSize, MyCallback callback) {
        Map<String ,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("relation",relation);
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        HttpFactory.create().Get(UrlUtils.getFenSi,map,callback);
    }


    //保持用户登录的cookie
    /**
     * 提交请求头信息
     * @param response
     */
    public void saveCookie(Response response) {
        cookie = "";
        Headers headers = response.headers();
        Set<String> names = headers.names();
        for (String name : names) {
            String value = headers.get(name);
            if (name.contains("Set-Cookie")) {
                cookie += value + ";";
            }
            if (cookie.length() > 0) {
                cookie = cookie.substring(0, cookie.length() - 1);
            }
        }
        mShared = App.activity.getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mShared.edit();
        mEditor.putString("cookie", cookie);
        mEditor.commit();

    }
}
