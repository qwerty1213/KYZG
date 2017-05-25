package test.bwie.com.example.ins7566.kyzg;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;

import static android.view.View.Z;

public class App extends Application {
    public static BaseActivity activity;
    public static BaseFragment lastFragment;

    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);

    }
}
