package test.bwie.com.example.ins7566.kyzg.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;
import test.bwie.com.example.ins7566.kyzg.bean.NewDetailBean;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

public class WebActivity extends BaseActivity {
    private INewsModel modle;
    @BindView(R.id.News_WebView)
    WebView NewsWebView;
    private String id;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (id != null) {
                        NewsWebView.loadUrl(url);
                    }
                    break;
            }
        }
    };
    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.webview_activity;
    }

    @Override
    protected void init() {
        modle = new NewsModelImpl();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.NewsDetail(id, new MyCallback() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", NewDetailBean.class);
                stream.alias("relative", NewDetailBean.NewsBean.RelativeBean.class);
                NewDetailBean bean = (NewDetailBean) stream.fromXML(response);
                url = bean.getNews().getUrl();
                handler.obtainMessage(1, url).sendToTarget();


//                Log.i("网址是", url);
                NewsWebView.setWebViewClient(new WebViewClient());
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
