package test.bwie.com.example.ins7566.kyzg.fragment.dongtanfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.adapter.ZanAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.bean.TweetNewBean;

public class ZanFragment extends BaseFragment {
    @BindView(R.id.dongtan_PullRecycler)
    PullToRefreshRecyclerView dongtanPullRecycler;
    private ArrayList<TweetNewBean.TweetBean.UserBean> list;
    private ZanAdapter adapter;

    public ArrayList<TweetNewBean.TweetBean.UserBean> getList() {
        adapter.notifyDataSetChanged();
        return list;
    }

    public void setList(ArrayList<TweetNewBean.TweetBean.UserBean> list) {
        this.list = list;
//        adapter.notifyDataSetChanged();

    }

    @Override
    protected int layoutId() {
        return R.layout.dongtan_activity;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        dongtanPullRecycler.setLayoutManager(manager);

    }

    @Override
    protected void initData() {
        adapter = new ZanAdapter(getContext(), list);
        dongtanPullRecycler.setAdapter(adapter);
        getList();
        adapter.notifyDataSetChanged();
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
//        ArrayList<Parcelable> like = bundle.getParcelableArrayList("like");

    }


}
