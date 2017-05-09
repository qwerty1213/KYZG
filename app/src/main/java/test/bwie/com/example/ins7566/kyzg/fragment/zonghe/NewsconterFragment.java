package test.bwie.com.example.ins7566.kyzg.fragment.zonghe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import test.bwie.com.example.ins7566.kyzg.App;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.activity.MainActivity;
import test.bwie.com.example.ins7566.kyzg.adapter.LunBotuAdapter;
import test.bwie.com.example.ins7566.kyzg.adapter.NewsConterAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.bean.NewsListBean;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

/**
 * Created by Administrator on 2017/4/12.
 */

public class NewsconterFragment extends BaseFragment {


    @BindView(R.id.lunbo_PullRecycler)
    PullToRefreshRecyclerView lunboPullRecycler;
    Unbinder unbinder;
    private ViewPager LunPoTuViewPager;
    private NewsConterAdapter adapter;
    private List<NewsListBean.NewsBean> mList;
    private INewsModel modle;
    private int Index = 0;
    private List<View> listView;
    private LunBotuAdapter MyAdapter;
    private int currentItem = 1000000;
    private final int CODE_start = 1;
    private final int CODE_END = 2;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_start:
                    LunPoTuViewPager.setCurrentItem(currentItem++);
                    handler.sendEmptyMessageDelayed(CODE_start, 2000);
                    break;
                case CODE_END:
                    handler.removeMessages(CODE_start);
                    break;
            }
        }
    };

    @Override
    protected int layoutId() {
        return R.layout.lunbotu_recycler;
    }

    //初始化数据
    @Override
    protected void initView(View view) {

        getInit();
    }

    private void getInit() {
        //加载轮播图的
        View view = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.activity_lunbotu, null);
        LunPoTuViewPager = (ViewPager) view.findViewById(R.id.LunPoTu_ViewPager);
        lunboPullRecycler.addHeaderView(view);
        //加载布局的

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        lunboPullRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lunboPullRecycler.setLayoutManager(linearLayoutManager);
        lunboPullRecycler.setPullRefreshEnabled(true);//下拉刷新
        //是否开启上拉加载功能
        lunboPullRecycler.setLoadingMoreEnabled(true);
        lunboPullRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                lunboPullRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lunboPullRecycler.setRefreshComplete();
                        mList.clear();
                        initData();
                    }
                }, 2000);
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
                }, 2000);
            }
        });
    }

    @Override
    protected void initData() {

        mList = new ArrayList<>();
        listView = new ArrayList<>();

        adapter = new NewsConterAdapter(getActivity(), mList);
        lunboPullRecycler.setAdapter(adapter);

    }

    @Override
    protected void initListener() {
      //轮播图
        MyAdapter = new LunBotuAdapter(listView);
        LunPoTuViewPager.setAdapter(MyAdapter);
        LunPoTuViewPager.setCurrentItem(currentItem++);
        handler.sendEmptyMessageDelayed(CODE_start, 2000);
        LunPoTuViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentItem = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //加载数据
    @Override
    protected void loadData() {
        getData();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.image_item1, null);
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.image_item2, null);
        View view2 = LayoutInflater.from(getContext()).inflate(R.layout.image_item3, null);
        listView.add(view);
        listView.add(view1);
        listView.add(view2);
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
            ((MainActivity) App.activity).getTitleText().setText("综合");
        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }

    private void getData() {
        modle = new NewsModelImpl();
        modle.news("1", String.valueOf(Index), "10", new MyCallback() {
            @Override
            public void onSuccess(String response) {
               XStream xStream=new XStream();
                xStream.alias("oschina",NewsListBean.class);
                xStream.alias("news",NewsListBean.NewsBean.class);
                NewsListBean newsListBean= (NewsListBean) xStream.fromXML(response);

                mList.addAll(newsListBean.getNewslist());

//                Log.i("数据是", response);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {

            }
        });
    }


}
