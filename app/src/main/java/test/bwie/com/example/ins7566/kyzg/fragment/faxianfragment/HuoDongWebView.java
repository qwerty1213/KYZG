package test.bwie.com.example.ins7566.kyzg.fragment.faxianfragment;

import android.content.Intent;
import android.webkit.WebView;

import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;
import test.bwie.com.example.ins7566.kyzg.bean.search.HuoDongDetailBean;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

public class HuoDongWebView extends BaseActivity {
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
        id = intent.getStringExtra("hd_detail");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
    modle.HuoDongDetail(id, new MyCallback() {
        @Override
        public void onSuccess(String response) {
            XStream stream = new XStream();
            stream.alias("oschina", HuoDongDetailBean.class);
            stream.alias("post",HuoDongDetailBean.PostBean.class);
            HuoDongDetailBean bean = (HuoDongDetailBean) stream.fromXML(response);
            String url = bean.getPost().getUrl();
            NewsWebView.loadUrl(url);
        }

        @Override
        public void onError(String error) {

        }
    });
    }


}
