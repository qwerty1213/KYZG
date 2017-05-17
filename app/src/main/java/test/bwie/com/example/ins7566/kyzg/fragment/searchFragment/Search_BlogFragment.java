package test.bwie.com.example.ins7566.kyzg.fragment.searchFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import test.bwie.com.example.ins7566.kyzg.App;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.adapter.search.SearchBlogAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.bean.search.SearchBean;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

import static android.os.Build.VERSION_CODES.N;

public class Search_BlogFragment extends BaseFragment {
    private INewsModel modle;
    private int Index;
    private String name;
    private SearchBlogAdapter adapter;
    private List<SearchBean.ResultBean> mList;
    private SharedPreferences mShared;
    @BindView(R.id.news_PullRecycler)
    PullToRefreshRecyclerView newsPullRecycler;

    @Override
    protected int layoutId() {
        return R.layout.news_contentfragment;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        newsPullRecycler.setLayoutManager(manager);
        newsPullRecycler.addItemDecoration(new DividerItemDecoration(App.activity, DividerItemDecoration.VERTICAL));
        newsPullRecycler.setPullRefreshEnabled(true);//刷新
        newsPullRecycler.setLoadingMoreEnabled(true);//加载
        newsPullRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                newsPullRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newsPullRecycler.setRefreshComplete();
                        mList.clear();
                        initData();
                    }
                }, 2000);

            }

            @Override
            public void onLoadMore() {
                newsPullRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newsPullRecycler.setLoadMoreComplete();
                        Index++;
                        loadData();
                    }
                }, 2000);
            }
        });

    }

    @Override
    protected void initData() {
        mShared = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        name = mShared.getString("Name", "");
        modle = new NewsModelImpl();
        mList = new ArrayList<>();
        adapter = new SearchBlogAdapter(getActivity(), mList);
        newsPullRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.getSearch("blog", name, String.valueOf(Index), "10", new MyCallback() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", SearchBean.class);
                stream.alias("result", SearchBean.ResultBean.class);
                SearchBean bean = (SearchBean) stream.fromXML(response);
                mList.addAll(bean.getResults());
                adapter.notifyDataSetChanged();
//                Log.e("查找的数据时", response);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    protected void onHiddn() {

    }

    @Override
    protected void show() {

    }

    @Override
    protected void unTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }


}
