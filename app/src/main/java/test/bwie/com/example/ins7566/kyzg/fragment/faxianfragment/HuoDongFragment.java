package test.bwie.com.example.ins7566.kyzg.fragment.faxianfragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import test.bwie.com.example.ins7566.kyzg.App;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.activity.MainActivity;
import test.bwie.com.example.ins7566.kyzg.adapter.LunBotuAdapter;
import test.bwie.com.example.ins7566.kyzg.adapter.huodong.HuoDongAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.bean.XianXiaHuoDongBean;
import test.bwie.com.example.ins7566.kyzg.config.ConfigFragment;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

import static android.os.Build.VERSION_CODES.N;

public class HuoDongFragment extends BaseFragment {
    @BindView(R.id.HD_Recycler)
    PullToRefreshRecyclerView HDRecycler;
    @BindView(R.id.HuoDong_Image)
    ImageView HuoDongImage;
    Unbinder unbinder;
    private INewsModel modle;
    private List<XianXiaHuoDongBean.EventBean> mList;
    private HuoDongAdapter adapter;
    private int Index = 0;
    private LunBotuAdapter lunBotuAdapter;
    private ViewPager LunPoTuViewPager;
    private List<View> mListView;
    private int currentItem = 1000000;
    private final int CODE_start = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_start:
                    LunPoTuViewPager.setCurrentItem(currentItem++);
                    handler.sendEmptyMessageDelayed(CODE_start, 2000);
                    break;
            }
        }
    };

    @Override
    protected int layoutId() {
        return R.layout.activity_huodong_recycler;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        HDRecycler.setLayoutManager(linearLayoutManager);
        HDRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        HDRecycler.setPullRefreshEnabled(true);//下拉刷新
        //是否开启上拉加载功能
        HDRecycler.setLoadingMoreEnabled(true);
        HDRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                HDRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HDRecycler.setRefreshComplete();

                        loadData();
                    }
                }, 2000);

            }

            @Override
            public void onLoadMore() {
                HDRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HDRecycler.setLoadMoreComplete();
                        Index++;
                        loadData();
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void initData() {
        //把轮播图添加到Title上
        View view = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.activity_lunbotu, null);
        LunPoTuViewPager = (ViewPager) view.findViewById(R.id.LunPoTu_ViewPager);
        HDRecycler.addHeaderView(view);

        /*
        初始化数据
         */
        modle = new NewsModelImpl();
        mList = new ArrayList<>();
        mListView = new ArrayList<>();
        //加载进适配器中
        adapter = new HuoDongAdapter(getContext(), mList);
        HDRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        //添加到适配器中，设置把图片添加到viewpager中，设置监听事件
        lunBotuAdapter = new LunBotuAdapter(mListView);
        LunPoTuViewPager.setAdapter(lunBotuAdapter);
        LunPoTuViewPager.setCurrentItem(currentItem++);
        //发送一个空消息，
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
        //加载view布局，图片
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_oschina_imageone, null);
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.activity_oschina_imagetwo, null);
        mListView.add(view);
        mListView.add(view1);
    }

    //加载数据
    private void getData() {
        modle.HuoDong(String.valueOf(Index), "0", new MyCallback() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", XianXiaHuoDongBean.class);
                stream.alias("event", XianXiaHuoDongBean.EventBean.class);
                XianXiaHuoDongBean bean = (XianXiaHuoDongBean) stream.fromXML(response);
                mList.addAll(bean.getEvents());
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
            ((MainActivity) App.activity).getMainTitleBar().setVisibility(View.GONE);
        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }




    @OnClick(R.id.HuoDong_Image)
    public void onViewClicked() {
        ConfigFragment.getInstance().init().start(FaxianFragment.class).build();
    }



}
