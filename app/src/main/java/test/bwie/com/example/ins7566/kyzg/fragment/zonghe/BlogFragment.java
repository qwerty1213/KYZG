package test.bwie.com.example.ins7566.kyzg.fragment.zonghe;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.adapter.BlogAdapter;
import test.bwie.com.example.ins7566.kyzg.adapter.LunBotuAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.bean.BloglistBean;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;

/**
 * Created by INS7566 on 2017/5/10.
 */

public class BlogFragment extends BaseFragment {
    @BindView(R.id.lunbo_PullRecycler)
    PullToRefreshRecyclerView lunboPullRecycler;
    Unbinder unbinder;
    private BlogAdapter adapter;
    private INewsModel modle;
    private List<BloglistBean.BlogBean> mList;
    private int Index = 0;

    @Override
    protected int layoutId() {
        return R.layout.lunbotu_recycler;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        lunboPullRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lunboPullRecycler.setLayoutManager(linearLayoutManager);
        lunboPullRecycler.setPullRefreshEnabled(true);
        lunboPullRecycler.setLoadingMoreEnabled(true);
        lunboPullRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
            lunboPullRecycler.post(new Runnable() {
                @Override
                public void run() {
                    lunboPullRecycler.setRefreshComplete();
                    mList.clear();
                    initData();
                }
            });
            }

            @Override
            public void onLoadMore() {
                    lunboPullRecycler.post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

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
