package test.bwie.com.example.ins7566.kyzg.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(layoutId(), container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        initData();
        loadData();
        initListener();

    }

    @Override
    public void onResume() {
        super.onResume();
        unTitleBar();
      loadData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onHiddn();
        } else {
            show();
        }
    }


    /*
    找ID
     */
    protected abstract int layoutId();

    /*
    初始化组件
     */
    protected abstract void initView(View view);
   /*
    初始化数据
     */

    protected abstract void initData();

    /*
    初始化监听
     */
    protected abstract void initListener();
    /*
    加载数据
     */

    protected abstract void loadData();

    /*
    当页面不可见时
     */
    protected abstract void onHiddn();

    /*
    当页面可见时
     */
    protected abstract void show();

    /*
    更改标题
     */

    protected abstract void unTitleBar();

    /*
    页面切换传递数据
     */
    public abstract void setParams(Bundle bundle);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
