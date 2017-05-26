package test.bwie.com.example.ins7566.kyzg.fragment.minefragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import test.bwie.com.example.ins7566.kyzg.activity.MainActivity;
import test.bwie.com.example.ins7566.kyzg.adapter.mine.FenSiAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.bean.FenSiBean;
import test.bwie.com.example.ins7566.kyzg.http.IMineModel;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.MineModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;


public class GuanZhuFragment extends BaseFragment {
    @BindView(R.id.dongtan_PullRecycler)
    PullToRefreshRecyclerView dongtanPullRecycler;
    private SharedPreferences mShared;
    private IMineModel modle;
    private List<FenSiBean.FriendBean> mList;
    private FenSiAdapter adapter;
    private int Index=0;

    @Override
    protected int layoutId() {
        return R.layout.dongtan_activity;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        dongtanPullRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dongtanPullRecycler.setLayoutManager(linearLayoutManager);
        dongtanPullRecycler.setPullRefreshEnabled(true);
        dongtanPullRecycler.setLoadingMoreEnabled(true);
        dongtanPullRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                dongtanPullRecycler.post(new Runnable() {
                    @Override
                    public void run() {
                        dongtanPullRecycler.setRefreshComplete();
                        mList.clear();
                        loadData();
                    }
                });
            }

            @Override
            public void onLoadMore() {
                dongtanPullRecycler.post(new Runnable() {
                    @Override
                    public void run() {
                        dongtanPullRecycler.setLoadMoreComplete();
                        Index++;
                        loadData();
                    }
                });
            }
        });
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        modle = new MineModelImpl();
        adapter = new FenSiAdapter(getContext(), mList);
        dongtanPullRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.getFensi("3407755","1","0","10", new MyCallback() {
            @Override

            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", FenSiBean.class);
                stream.alias("friend", FenSiBean.FriendBean.class);
                FenSiBean bean = (FenSiBean) stream.fromXML(response);
                mList.addAll(bean.getFriends());
                Log.d("FenSiFragment:我的关注", response);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {

            }
        });
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
        if (App.activity instanceof MainActivity) {
            ((MainActivity) App.activity).getMainRadioGroup().setVisibility(View.GONE);
        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }

}
