package test.bwie.com.example.ins7566.kyzg.http.okhttp;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.security.Key;
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
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by INS7566 on 2017/5/8.
 */

public class OKHttpUtils implements IHttp {
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private String cookie;
    private OKHttpUtils(){

    }
    public static OKHttpUtils  okHttpUtils=new OKHttpUtils();
    public static OKHttpUtils getInstance(){
        return okHttpUtils;
    }

    @Override
    public void Post(String url, Map<String, String> params, final MyCallback callback) {
        OkHttpClient okHttpClient=new OkHttpClient.Builder().build();
        FormBody.Builder builder=new FormBody.Builder();
        if (params!=null){
            Set<String> keySet=params.keySet();
            for (String key:keySet){
                String value =params.get(key);
                builder.add(key,value);
            }
            Request request=new Request.Builder()
                    .url(url)
                    .post(builder.build())
                    .addHeader("Cookie",getCookie())
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    App.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    saveCookie(response);
                    final String string =response.body().string();
                    App.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(string);
                        }
                    });
                }
            });
        }
    }

    /**
     * 保存cookie值
     * @return
     */
    public String getCookie(){
        String cookie="";
        mShared= App.activity.getSharedPreferences("data", MODE_PRIVATE);
        cookie=mShared.getString("cookie","");
        return cookie;
    }

    public  void saveCookie(Response response){
        cookie="";
        Headers headers=response.headers();
        Set<String> names=headers.names();
        for (String name:names){
            String value=headers.get(name);
            if (name.contains("Set-Cookie")){
                cookie+=value+"+";
            }
            if (cookie.length()>0){
                cookie=cookie.substring(0,cookie.length()-1);
            }
        }
        mShared=App.activity.getSharedPreferences("data",MODE_PRIVATE);
        mEditor=mShared.edit();
        mEditor.putString("cookie",cookie);
        mEditor.commit();
    }
    @Override
    public void Get(String url, Map<String, String> params, final MyCallback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                String value = params.get(key);
                builder.add(key, value);

            }
            Request request = new Request.Builder()
                    .url(url)
                    .post(builder.build())
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String str = response.body().string();
                    App.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callback.onSuccess(str);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
    }
}
