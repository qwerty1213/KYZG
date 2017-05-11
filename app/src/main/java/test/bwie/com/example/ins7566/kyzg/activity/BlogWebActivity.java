package test.bwie.com.example.ins7566.kyzg.activity;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;
import test.bwie.com.example.ins7566.kyzg.bean.BlogDetailBean;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

public class BlogWebActivity extends BaseActivity {
    @BindView(R.id.News_WebView)
    WebView NewsWebView;
    private INewsModel modle;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.webview_activity;
    }

    @Override
    protected void init() {
        modle = new NewsModelImpl();
        Intent intent = getIntent();
        id = intent.getStringExtra("BlogId");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.BlogDetail(id, new MyCallback() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", BlogDetailBean.class);
                BlogDetailBean bean = (BlogDetailBean) stream.fromXML(response);
                String url = bean.getBlog().getUrl();
                NewsWebView.loadUrl(url);
                Log.i("解析的网址是", url);
                NewsWebView.setWebViewClient(new WebViewClient());
            }

            @Override
            public void onError(String error) {

            }
        });
    }

}
