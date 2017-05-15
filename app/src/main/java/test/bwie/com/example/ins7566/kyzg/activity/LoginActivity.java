package test.bwie.com.example.ins7566.kyzg.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;
import test.bwie.com.example.ins7566.kyzg.bean.LoginBean;
import test.bwie.com.example.ins7566.kyzg.db.MyManger;
import test.bwie.com.example.ins7566.kyzg.http.IMineModel;
import test.bwie.com.example.ins7566.kyzg.http.MineModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

/**
 * Created by INS7566 on 2017/5/11.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_zhanghao)
    EditText loginZhanghao;
    @BindView(R.id.login_mima)
    EditText loginMima;
    @BindView(R.id.login_denglu)
    Button loginDenglu;
    @BindView(R.id.login_zhuce)
    Button loginZhuce;
    private SharedPreferences mShared;
    private SharedPreferences.Editor meditor;
    private String name,pwd;
    private LoginBean bean;
    private String cookie;
   private MyManger manger;
    private String name1;
    private String pwd1;
    private IMineModel modle;

    @Override
    protected int getLayoutId() {
        return R.layout.mine_login;
    }

    @Override
    protected void init() {
        modle=new MineModelImpl();
        manger=new MyManger(this);
        mShared=getSharedPreferences("data",MODE_PRIVATE);
        meditor=mShared.edit();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }
    private void Login(){
        modle.Login(loginZhanghao.getText().toString().trim(), loginMima.getText().toString().trim(), "1", new MyCallback() {
            @Override
            public void onSuccess(String response) {
                XStream xStream=new XStream();
                xStream.alias("oschina",LoginBean.class);
                bean= (LoginBean) xStream.fromXML(response);
                if (bean.getResult().getErrorCode().equals("1")) {
                    meditor.putString("sendMsg", bean.getUser().getUid());
                    meditor.putString("userName", bean.getUser().getName());
                    meditor.putString("port", bean.getUser().getPortrait());
//                    Log.d("fds","qw"+response);
                    meditor.commit();
//                    Log.d("haha","abc"+response);
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else if (bean.getResult().getErrorCode().equals("0")) {
                    Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onError(String error) {
                Log.d("er",error);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_zhanghao, R.id.login_mima, R.id.login_denglu, R.id.login_zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_zhanghao:
                break;
            case R.id.login_mima:
                break;
            case R.id.login_denglu:
                name1 = loginZhanghao.getText().toString().trim();
                pwd1 = loginMima.getText().toString().trim();
                if (name1.isEmpty() && pwd1.isEmpty()) {
                    Toast.makeText(this, "请输入正确的信息", Toast.LENGTH_SHORT).show();
                } else {
                    Login();
                }

                break;
            case R.id.login_zhuce:
                break;
        }
    }
}
