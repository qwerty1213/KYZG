package test.bwie.com.example.ins7566.kyzg.fragment.zonghe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import test.bwie.com.example.ins7566.kyzg.App;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.activity.LoginActivity;
import test.bwie.com.example.ins7566.kyzg.activity.MainActivity;
import test.bwie.com.example.ins7566.kyzg.adapter.FragAdapter;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.fragment.faxianfragment.FaxianFragment;
import test.bwie.com.example.ins7566.kyzg.fragment.jiahao.DragAdapter;
import test.bwie.com.example.ins7566.kyzg.fragment.jiahao.DragGridView;
import test.bwie.com.example.ins7566.kyzg.fragment.jiahao.PopupColuActivity;

import static android.R.attr.y;
import static android.os.Build.VERSION_CODES.N;

public class NewsFragment extends BaseFragment implements View.OnClickListener {
    Unbinder unbinder;
    private List<String> listName;
    private List<BaseFragment> mList;
    private FragAdapter adapter;
    @BindView(R.id.newsTabLayout)
    TabLayout newsTabLayout;
    @BindView(R.id.newsViewPager)
    ViewPager newsViewPager;
    private ImageView addImage;
    private PopupWindow popupMenu;
    private View main_title;
    private LinearLayout my_grid_ll;
    private boolean mFlag=true;
    private RelativeLayout mGridTitle;
    private boolean iv_rotation = true;
    private Animation animation;
    private Animation animation1;
    private DragGridView gridView;
    private DragGridView gridView_other;
    private DragAdapter dragAdapter;
    private DragAdapter other_adapter;
    private ArrayList<BaseFragment> fragmentList = new ArrayList<BaseFragment>(); //碎片链表
    private PopupWindow popupWindow;
    private ArrayList<String> channels = new ArrayList<>();
    private ArrayList<String> channels_other = new ArrayList<>();


    //找ID布局文件
    @Override
    protected int layoutId() {
        return R.layout.news_fragment;
    }

    //初始化组件
    @Override
    protected void initView(View view) {
        addImage = (ImageView) view.findViewById(R.id.addimg);
        main_title = view.findViewById(R.id.Main_TitleBar);
    }

    //加载数据
    @Override
    protected void initData() {
        listName = new ArrayList<>();
        mList = new ArrayList<>();

        //加载Fragment类
        mList.add(new ZiXun());
        mList.add( new BlogFragment());
        mList.add(new HotSpotFragment());
        mList.add(new RecommendFragment());
        listName.add("资讯");
        listName.add("博客");
        listName.add("热点");
        listName.add("推荐");
        adapter = new FragAdapter(getFragmentManager(), listName, mList);
        newsViewPager.setAdapter(adapter);
        newsTabLayout.setupWithViewPager(newsViewPager);

    }

    //监听事件
    @Override
    protected void initListener() {
            addImage.setOnClickListener(this);
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

    @Override
    public void onClick( View v) {
        switch (v.getId()){
            case R.id.addimg:
                Intent intent=new Intent(getActivity().getApplication().getApplicationContext(), PopupColuActivity.class);
                startActivityForResult(intent,0);
        break;
        }
    }

}
