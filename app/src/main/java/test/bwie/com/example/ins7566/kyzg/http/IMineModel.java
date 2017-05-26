package test.bwie.com.example.ins7566.kyzg.http;

import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

/**
 * Created by INS7566 on 2017/5/11.
 */

public interface IMineModel {
    void Login(String username, String pwd, String keep_login, MyCallback callback);
    void getFensi(String uid,String relation,String pageIndex,String pageSize,MyCallback callback);
}
