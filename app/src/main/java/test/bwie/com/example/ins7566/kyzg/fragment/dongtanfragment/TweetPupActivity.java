package test.bwie.com.example.ins7566.kyzg.fragment.dongtanfragment;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;
import test.bwie.com.example.ins7566.kyzg.db.MyManger;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.os.Build.VERSION_CODES.N;

public class TweetPupActivity extends BaseActivity {
    @BindView(R.id.Tweet_SendMsg)
    TextView TweetSendMsg;
    @BindView(R.id.Tweet_EditText)
    EditText TweetEditText;
    private SharedPreferences mShared;
    private INewsModel modle;
    private String sendMsg;
    private MyManger manger;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tweet_pub;
    }

    @Override
    protected void init() {
        modle = new NewsModelImpl();
        mShared = getSharedPreferences("data", MODE_PRIVATE);
        sendMsg = mShared.getString("sendMsg", "");
        manger = new MyManger(getApplicationContext());
//        id = manger.QueryUid();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {


    }

    private void sendMsg() {
        modle.sendMsg(sendMsg, TweetEditText.getText().toString(), "", "", new MyCallback() {
            @Override
            public void onSuccess(String response) {
//                Log.d("TweetPupActivity+我的是", response);
//                Toast.makeText(TweetPupActivity.this, response, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

            @Override
            public void onError(String error) {

            }
        });
    }


    @OnClick(R.id.Tweet_SendMsg)
    public void onViewClicked() {
        if(TweetEditText.getText().toString().isEmpty()){
            Toast.makeText(this, "请输入内容", 0).show();
        }else{
            sendMsg();
            Toast.makeText(this, "正在发表动弹", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "你的动弹发表成功", Toast.LENGTH_SHORT).show();
        }

    }

    //退出键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
