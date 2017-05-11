package test.bwie.com.example.ins7566.kyzg.fragment.zonghe;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.adapter.NewsConterAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.bean.NewsListBean;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

/**
 * Created by INS7566 on 2017/5/10.
 */

public class HotSpotFragment extends BaseFragment {

    @BindView(R.id.lunbo_PullRecycler)
    PullToRefreshRecyclerView lunboPullRecycler;
    private List<NewsListBean.NewsBean> mList;
    private INewsModel model;
    private NewsConterAdapter adapter;
    private int Index = 0;

    @Override
    protected int layoutId() {
        return R.layout.lunbotu_recycler;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        lunboPullRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        lunboPullRecycler.setLayoutManager(linearLayoutManager);
        lunboPullRecycler.setPullRefreshEnabled(true);
        lunboPullRecycler.setLoadingMoreEnabled(true);
        lunboPullRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                lunboPullRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lunboPullRecycler.setRefreshComplete();
                        mList.clear();
                        loadData();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                lunboPullRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lunboPullRecycler.setLoadMoreComplete();
                        Index++;
                        loadData();
                    }
                },2000);

            }
        });

    }

    @Override
    protected void initData() {
        mList=new ArrayList<>();
        model=new NewsModelImpl();
        adapter=new NewsConterAdapter(getActivity(),mList);
        lunboPullRecycler.setAdapter(adapter);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        model.HotSpot("5", String.valueOf(Index), "5", "", new MyCallback() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
