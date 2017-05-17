package test.bwie.com.example.ins7566.kyzg.fragment.searchFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.adapter.search.PeopleAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.bean.search.SeachPeopleBean;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

import static android.os.Build.VERSION_CODES.N;

public class Search_PeopleFragment extends BaseFragment {
    private INewsModel modle;
    private List<SeachPeopleBean.UserBean> mList;
    private SharedPreferences mShared;
    @BindView(R.id.news_PullRecycler)
    PullToRefreshRecyclerView newsPullRecycler;
    Unbinder unbinder;
    private String name;
    private PeopleAdapter adapter;

    @Override
    protected int layoutId() {
        return R.layout.news_contentfragment;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        newsPullRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        newsPullRecycler.setLayoutManager(manager);

    }


    @Override
    protected void initData() {
        modle = new NewsModelImpl();
        mList = new ArrayList<>();
        mShared = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        name = mShared.getString("Name", "");
        adapter = new PeopleAdapter(getContext(), mList);
        newsPullRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.SerachPeople(name, new MyCallback() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", SeachPeopleBean.class);
                stream.alias("user", SeachPeopleBean.UserBean.class);
                SeachPeopleBean bean = (SeachPeopleBean) stream.fromXML(response);
                mList.addAll(bean.getUsers());
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

}
