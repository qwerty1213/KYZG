package test.bwie.com.example.ins7566.kyzg.fragment.zonghefragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import test.bwie.com.example.ins7566.kyzg.App;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.activity.MainActivity;
import test.bwie.com.example.ins7566.kyzg.adapter.FragAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;

/**
 * Created by INS7566 on 2017/5/8.
 */

public class NewsFragment extends BaseFragment {
    @BindView(R.id.newsTabLayout)
    TabLayout newsTabLayout;
    @BindView(R.id.newsViewPager)
    ViewPager newsViewPager;
    Unbinder unbinder;
    private List<String> listName;
    private List<BaseFragment> mList;
    private FragAdapter adapter;
    @Override
    protected int layoutId() {
        return R.layout.news_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        listName = new ArrayList<>();
        mList = new ArrayList<>();
        //加载Fragment类
        mList.add(new NewscontentFragment());
        mList.add(new NewscontentFragment());
        mList.add(new NewscontentFragment());
        mList.add(new NewscontentFragment());

        listName.add("资讯");
        listName.add("博客");
        listName.add("热点");
        listName.add("推荐");
        adapter = new FragAdapter(getFragmentManager(), listName, mList);
        newsViewPager.setAdapter(adapter);
        newsTabLayout.setupWithViewPager(newsViewPager);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

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
            //显示
            ((MainActivity) App.activity).getMainTitleBar().setVisibility(View.VISIBLE);
            ((MainActivity) App.activity).getMainRadioGroup().setVisibility(View.VISIBLE);

        }
        if (App.activity instanceof MainActivity) {
            ((MainActivity) App.activity).getTitleText().setText("综合");
        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }

}
