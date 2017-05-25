package test.bwie.com.example.ins7566.kyzg.fragment.zonghe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.adapter.LunBotuAdapter;
import test.bwie.com.example.ins7566.kyzg.adapter.NewsConterAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.bean.LunBoListBean;
import test.bwie.com.example.ins7566.kyzg.bean.NewsListBean;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

public class ZiXun extends BaseFragment {


    @BindView(R.id.lunbo_PullRecycler)
    PullToRefreshRecyclerView lunboPullRecycler;
    private NewsConterAdapter adapter;
    private List<NewsListBean.NewsBean> mList;
    private INewsModel modle;
    private int Index = 0;

    private List<View> viewList;
    private LunBotuAdapter lunBoAdapter;
    private int currentItem = 1000000;
    private final int START = 1;
    private final int END = 2;
    private ViewPager viewPager;
    private View view;
    private RadioButton radioBut1, radioBut2, radioBut3, radioBut4,radioBut5,radioBut6;
    List<LunBoListBean.ListBean> beanList;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case START:
                    viewPager.setCurrentItem(currentItem++);
                    handler.sendEmptyMessageDelayed(START, 5000);
                    break;
                case END:
                    handler.removeMessages(END);
                    break;
            }
        }
    };

    @Override
    protected int layoutId() {
        return R.layout.lunbotu_recycler;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lunboPullRecycler.setLayoutManager(linearLayoutManager);
        lunboPullRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
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

                        loadData();
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
        modle = new NewsModelImpl();
        mList = new ArrayList<>();
        viewList = new ArrayList<>();
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.activity_luobo_viewpager, null);
        viewPager = (ViewPager) view1.findViewById(R.id.LunBoTuPager);
        radioBut1 = (RadioButton) view1.findViewById(R.id.RadioBut1);
        radioBut2 = (RadioButton) view1.findViewById(R.id.RadioBut2);
        radioBut3 = (RadioButton) view1.findViewById(R.id.RadioBut3);
        radioBut4 = (RadioButton) view1.findViewById(R.id.RadioBut4);
        radioBut5 = (RadioButton) view1.findViewById(R.id.RadioBut5);
        radioBut6 = (RadioButton) view1.findViewById(R.id.RadioBut6);

        lunboPullRecycler.addHeaderView(view1);
        lunBoAdapter = new LunBotuAdapter(viewList);
        viewPager.setAdapter(lunBoAdapter);

    }


    @Override
    protected void initListener() {

        handler.sendEmptyMessageDelayed(START, 5000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position % viewList.size() == 0) {// arg0表示起始页，arg1表示偏移的百分比，arg2表示偏移的像素
                    radioBut1.setChecked(true);
                } else if (position % viewList.size() == 1) {
                    radioBut2.setChecked(true);
                } else if (position % viewList.size() == 2) {
                    radioBut3.setChecked(true);
                } else if (position % viewList.size() == 3) {
                    radioBut4.setChecked(true);
                } else if (position % viewList.size()==4){
                    radioBut5.setChecked(true);
                } else if (position % viewList.size()==5){
                    radioBut6.setChecked(true);
                }
            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
                if (position % viewList.size() == 0) {// arg0表示起始页，arg1表示偏移的百分比，arg2表示偏移的像素
                    radioBut1.setChecked(true);
                } else if (position % viewList.size() == 1) {
                    radioBut2.setChecked(true);
                } else if (position % viewList.size() == 2) {
                    radioBut3.setChecked(true);
                } else if (position % viewList.size() == 3) {
                    radioBut4.setChecked(true);
                }else if (position % viewList.size()==4){
                    radioBut5.setChecked(true);
                }else if (position % viewList.size()==5){
                    radioBut6.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void loadData() {
        getData();

        lunbodata();//轮播解析

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

    //轮播解析
    private void lunbodata() {
        modle.ipanda(new MyCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                LunBoListBean bean = gson.fromJson(response, LunBoListBean.class);
                /**
                 * 这是循环得到图片加载
                 */
                beanList = bean.getList();
                for (int i = 0; i < beanList.size(); i++) {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.lunbo_item, null);
                    ImageView image = (ImageView) view.findViewById(R.id.lunbo_image);
                    Glide.with(getContext()).load(beanList.get(i).getImage()).into(image);
                    viewList.add(view);
//                    Log.d("MainActivity", "轮播图" + response);
                }
                lunBoAdapter.notifyDataSetChanged();
                handler.sendEmptyMessageDelayed(START, 2000);
            }

            @Override
            public void onError(String error) {

            }


        });
    }

    @Override
    public void setParams(Bundle bundle) {

    }



    private void getData() {
        modle.news("1", String.valueOf(Index), "20", new MyCallback() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", NewsListBean.class);
                stream.alias("news", NewsListBean.NewsBean.class);
                NewsListBean bean = (NewsListBean) stream.fromXML(response);
//                Log.i("博客数据是", response);
                mList.addAll(bean.getNewslist());
                adapter = new NewsConterAdapter(getActivity(), mList);
                lunboPullRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {

            }
        });
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