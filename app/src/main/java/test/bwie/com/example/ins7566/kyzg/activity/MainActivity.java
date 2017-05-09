package test.bwie.com.example.ins7566.kyzg.activity;

import android.content.SharedPreferences;
import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import test.bwie.com.example.ins7566.kyzg.App;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.config.ConfigFragment;
import test.bwie.com.example.ins7566.kyzg.fragment.zonghe.NewsFragment;

public class MainActivity extends BaseActivity {
    @BindView(R.id.Main_TitleBar)
    RelativeLayout MainTitleBar;
    @BindView(R.id.FrameLayout_contentGroup)
    FrameLayout FrameLayoutContentGroup;
    @BindView(R.id.Title_Text)
    TextView TitleText;
    @BindView(R.id.Serch_Btn)
    ImageView SerchBtn;
    @BindView(R.id.ZongHeBtn)
    RadioButton ZongHeBtn;
    @BindView(R.id.DongTanBtn)
    RadioButton DongTanBtn;
    @BindView(R.id.AddBtn)
    ImageView AddBtn;
    @BindView(R.id.FaXianBtn)
    RadioButton FaXianBtn;
    @BindView(R.id.MineBtn)
    RadioButton MineBtn;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.Main_RadioGroup)
    RadioGroup MainRadioGroup;
    private FragmentManager fragmentManager;
    private SharedPreferences mShared;
    private String uid;
    private SharedPreferences.Editor mEditor;

    //找组件布
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void initListener() {

    }//

    //加载数据执行方法
    @Override
    protected void loadData() {
        ConfigFragment.getInstance().init().start(NewsFragment.class).build();

    }

    @OnClick({R.id.ZongHeBtn, R.id.AddBtn, R.id.DongTanBtn, R.id.FaXianBtn, R.id.MineBtn, R.id.Serch_Btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ZongHeBtn:
                ConfigFragment.getInstance().init().start(NewsFragment.class).build();
                break;
            case R.id.DongTanBtn:

                break;
            case R.id.FaXianBtn:

                break;
            case R.id.MineBtn:

                break;
            case R.id.Serch_Btn:

                break;
            case R.id.AddBtn:

                break;
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager.BackStackEntry entryAt = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
        //得到每一个位于栈顶的类的名字，然后执行Finish方法进行弹栈
        String name = entryAt.getName();
        if ("TweetFragment".equals(name) ||
                "FaxianFragment".equals(name) ||
                "Mine_Fragment".equals(name) ||
                "NewsFragment".equals(name)
                ) {
            finish();
        } else {
            if (fragmentManager.getBackStackEntryCount() > 1) {
                fragmentManager.popBackStackImmediate();//执行弹栈，立马执行
                //否则记录得到位于栈顶的类名字
                String simpleName = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
                //记录做标记，标记为上一个Fragment,点击back键刷新lastFragment
                App.lastFragment = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);

            }
        }


    }

    public TextView getTiitle() {
        return TitleText;
    }

    //执行完全退出
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Process.killProcess(Process.myPid());//获取pid
        System.exit(0);
    }

    //隐藏下面的RadioGroup
    public RadioGroup getMainRadioGroup() {
        return MainRadioGroup;
    }

    public void setMainRadioGroup(RadioGroup mainRadioGroup) {
        MainRadioGroup = mainRadioGroup;
    }

    //隐藏title的
    public void setTitleText(TextView titleText) {
        TitleText = titleText;
    }

    public TextView getTitleText() {
        return TitleText;
    }

    public RelativeLayout getMainTitleBar() {
        return MainTitleBar;
    }

    public void setMainTitleBar(RelativeLayout mainTitleBar) {
        MainTitleBar = mainTitleBar;
    }
}
