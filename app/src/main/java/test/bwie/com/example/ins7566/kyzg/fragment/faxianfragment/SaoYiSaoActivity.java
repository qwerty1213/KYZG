package test.bwie.com.example.ins7566.kyzg.fragment.faxianfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.activity.MainActivity;
import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;



public class SaoYiSaoActivity extends BaseActivity {
    private final static int REQ_CODE = 1028;


    @Override
    protected int getLayoutId() {
        return R.layout.saoyisaoactivity;
    }

    @Override
    protected void init() {
        Intent intent = new Intent(SaoYiSaoActivity.this, CaptureActivity.class);
        startActivityForResult(intent,REQ_CODE);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(SaoYiSaoActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }else{
                finish();
            }
        }else{
            finish();
        }
    }


    private void showToast(String msg) {
        Toast.makeText(SaoYiSaoActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
    }
}

