package test.bwie.com.example.ins7566.kyzg;

import android.app.Application;

import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;

public class App extends Application {
    public static BaseActivity activity;
    public static BaseFragment lastFragment;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
