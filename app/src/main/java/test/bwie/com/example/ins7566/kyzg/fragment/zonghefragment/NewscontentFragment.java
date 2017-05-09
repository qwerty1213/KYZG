package test.bwie.com.example.ins7566.kyzg.fragment.zonghefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import test.bwie.com.example.ins7566.kyzg.App;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.activity.MainActivity;
import test.bwie.com.example.ins7566.kyzg.adapter.NewsAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.bean.NewsListBean;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;


/**
 * Created by INS7566 on 2017/5/9.
 */

public class NewscontentFragment extends BaseFragment {

    @BindView(R.id.lunbo_PullRecycler)
    PullToRefreshRecyclerView lunboPullRecycler;
    Unbinder unbinder;
    private NewsAdapter adapter;
    private List<NewsListBean.NewsBean> mList;
    private INewsModel model;
    private int Index=0;

    @Override
    protected int layoutId() {
        return R.layout.lunbotu_recycler;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        model=new NewsModelImpl();
        mList=new ArrayList<>();
        adapter=new NewsAdapter(getActivity(),mList);
        lunboPullRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }
//加载数据
    @Override
    protected void loadData() {
        getData();
    }

    @Override
    protected void onHiddn() {
        unTitleBar();

    }

    @Override
    protected void show() {
        unTitleBar();
    }

    @Override
    protected void unTitleBar() {
        if (App.activity instanceof MainActivity){
            ((MainActivity) App.activity).getTitleText().setText("综合");
        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }

   private void getData(){
       model=new NewsModelImpl();
       model.news("1", String.valueOf(Index), "10", new MyCallback() {
           @Override
           public void onSuccess(String response) {
               XStream xStream=new XStream();
               xStream.alias("oschina",NewsListBean.class);
               xStream.alias("news",NewsListBean.NewsBean.class);
               NewsListBean newsListBean= (NewsListBean) xStream.fromXML(response);
               mList.addAll(newsListBean.getNewslist());
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onError(String error) {

           }
       });
   }
}
