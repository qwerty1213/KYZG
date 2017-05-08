package test.bwie.com.example.ins7566.kyzg.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import test.bwie.com.example.ins7566.kyzg.R;

/**
 * Created by INS7566 on 2017/5/8.
 */

public class DonghuaActivity extends Activity {
    private final static String TAG="DonghuaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donghua);

        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**
         * 设置时间
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(DonghuaActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
