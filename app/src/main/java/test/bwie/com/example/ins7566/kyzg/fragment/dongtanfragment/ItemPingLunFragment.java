package test.bwie.com.example.ins7566.kyzg.fragment.dongtanfragment;

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
import test.bwie.com.example.ins7566.kyzg.adapter.DongTanPingLunAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.bean.HuoQuPingLunBean;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

import static java.security.AccessController.getContext;

public class ItemPingLunFragment extends BaseFragment {
    @BindView(R.id.dongtan_PullRecycler)
    PullToRefreshRecyclerView dongtanPullRecycler;
    private INewsModel modle;
    private SharedPreferences mShared;
    private DongTanPingLunAdapter adapter;
    private List<HuoQuPingLunBean.CommentBean> mList;
    private String id;
    private int Index = 0;

    @Override
    protected int layoutId() {
        return R.layout.dongtan_activity;
    }

    @Override
    protected void initView(View view) {
        dongtanPullRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dongtanPullRecycler.setLayoutManager(linearLayoutManager);
        dongtanPullRecycler.setPullRefreshEnabled(true);//下拉刷新
        //是否开启上拉加载功能
        dongtanPullRecycler.setLoadingMoreEnabled(true);
        dongtanPullRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                dongtanPullRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dongtanPullRecycler.setRefreshComplete();
                        mList.clear();
                        loadData();
                    }
                }, 2000);


            }

            @Override
            public void onLoadMore() {
                dongtanPullRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dongtanPullRecycler.setLoadMoreComplete();
                        Index++;
                        loadData();

                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void initData() {
        modle = new NewsModelImpl();
        mList = new ArrayList<>();
        mShared = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
        id = mShared.getString("tweet_id", "");
        Log.d("ItemPingLunFragment我的id", id);
        adapter = new DongTanPingLunAdapter(getContext(), mList);
        dongtanPullRecycler.setAdapter(adapter);
        Log.d("ItemPingLunFragment", "adapter:" + adapter);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.getPinlun("3", id, String.valueOf(Index), "10", new MyCallback() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", HuoQuPingLunBean.class);
                stream.alias("comment", HuoQuPingLunBean.CommentBean.class);

                HuoQuPingLunBean bean = (HuoQuPingLunBean) stream.fromXML(response);
                mList.addAll(bean.getComments());
                adapter.notifyDataSetChanged();
                Log.d("ItemPingLunFragment", response);

            }

            @Override
            public void onError(String error) {
                Log.d("ItemPingLunFragment", error);
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
