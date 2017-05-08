package test.bwie.com.example.ins7566.kyzg.base;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import test.bwie.com.example.ins7566.kyzg.App;


public abstract class BaseActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        App.activity = this;
        fragmentManager = getSupportFragmentManager();
        init();
        initListener();
        loadData();
        if (ContextCompat.checkSelfPermission(BaseActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(BaseActivity.this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.VIBRATE}, 1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //记住当前的BaseActivity
        App.activity = this;
        //当页面可见时加载数据
    }

    //    /**
//     * 用于fragment切换
//     *
//     * @param targetFragment 要跳转的目标Fragment
//     * @param params         跳转页面携带的参数
//     * @param isBack         是否通过back键返回
//     */
    //回退站执行父类的回退栈方法
//    @Override
//    public void onBackPressed() {
        //判断得到哪一个类位于栈顶
//        FragmentManager.BackStackEntry entryAt = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
//        //得到每一个位于栈顶的类的名字，然后执行Finish方法进行弹栈
//        String name = entryAt.getName();
//        if ("TweetFragment".equals(name) ||
//                "FaxianFragment".equals(name) ||
//                "Mine_Fragment".equals(name) ||
//                "NewsFragment".equals(name)
//                ) {
//            finish();
//        } else {
//            if (fragmentManager.getBackStackEntryCount() > 1) {
//                fragmentManager.popBackStackImmediate();//执行弹栈，立马执行
//                //否则记录得到位于栈顶的类名字
//                String simpleName = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
//                //记录做标记，标记为上一个Fragment,点击back键刷新lastFragment
//                App.lastFragment = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);
//
//            }
//        }

//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //加载布局
    protected abstract int getLayoutId();

    //初始化
    protected abstract void init();

    //初始化监听
    protected abstract void initListener();

    //加载数据
    protected abstract void loadData();

}
