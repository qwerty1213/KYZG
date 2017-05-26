package test.bwie.com.example.ins7566.kyzg.fragment.dongtanfragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.bwie.com.example.ins7566.kyzg.App;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.activity.LoginActivity;
import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;
import test.bwie.com.example.ins7566.kyzg.config.ConfigFragment;
import test.bwie.com.example.ins7566.kyzg.db.MyManger;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.os.Build.VERSION_CODES.N;

public class TweetPupActivity extends BaseActivity {
    @BindView(R.id.wdb_back)
    ImageView wdbBack;
    @BindView(R.id.add_send)
    TextView addSend;
    @BindView(R.id.add_edit)
    EditText addEdit;
    @BindView(R.id.add_tupian)
    ImageView addTupian;
    @BindView(R.id.add_bieren)
    ImageView addBieren;
    @BindView(R.id.add_huati)
    ImageView addHuati;
    @BindView(R.id.add_biaoqing)
    ImageView addBiaoqing;
    private SharedPreferences mShared;
    private INewsModel modle;
    private String uid;

    private String msg;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_tweet_pub;
    }

    @Override
    protected void init() {
        modle = new NewsModelImpl();
        mShared = getSharedPreferences("data", MODE_PRIVATE);
        uid = mShared.getString("sendMsg", "");


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.wdb_back, R.id.add_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wdb_back:
                App.activity.onBackPressed();
                break;
            case R.id.add_send:
                if (addEdit.getText().toString().isEmpty()) {
                    Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                } else {
                    sendMsg();
                }
                break;
        }
    }

    private void sendMsg() {

        if (uid.isEmpty()){

            Intent intent=new Intent(TweetPupActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        msg=addEdit.getText().toString();
        modle.sendMsg(uid, msg, "", "", new MyCallback() {

            @Override
            public void onSuccess(String response) {
//                FragmentBuilder.getInstance().start(MyFraA.class).isBack(true).build();
                Log.d("动弹发表","动弹id"+uid);
                Log.d("动弹发送内容","动弹id"+msg);
                Log.d("动弹发表结果","===========///////"+response);
                onBackPressed();
            }

            @Override
            public void onError(String error) {

            }
        });
    }

}
