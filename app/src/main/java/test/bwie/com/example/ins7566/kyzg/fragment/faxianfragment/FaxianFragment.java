package test.bwie.com.example.ins7566.kyzg.fragment.faxianfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import test.bwie.com.example.ins7566.kyzg.App;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.activity.LoginActivity;
import test.bwie.com.example.ins7566.kyzg.activity.MainActivity;
import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;
import test.bwie.com.example.ins7566.kyzg.config.ConfigFragment;
import test.bwie.com.example.ins7566.kyzg.fragment.kaiyuansoftware.KYSoftware;

/**
 * Created by INS7566 on 2017/5/17.
 */

public class FaxianFragment extends BaseFragment {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.find_mayun)
    LinearLayout findMayun;
    @BindView(R.id.kaiyuanruanjian)
    LinearLayout kaiyuanruanjian;
    @BindView(R.id.saoyisao)
    LinearLayout saoyisao;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.yaoyiyao)
    LinearLayout yaoyiyao;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.jiaoyin)
    ImageView jiaoyin;
    @BindView(R.id.fujinchengxuyuan)
    LinearLayout fujinchengxuyuan;
    Unbinder unbinder;
    @BindView(R.id.xianxiahuodong)
    LinearLayout xianxiahuodong;

    @Override
    protected int layoutId() {
        return R.layout.find_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

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
        if (App.activity instanceof MainActivity) {
            //显示
            ((MainActivity) App.activity).getMainTitleBar().setVisibility(View.VISIBLE);
            ((MainActivity) App.activity).getMainRadioGroup().setVisibility(View.VISIBLE);

        }
        if (App.activity instanceof MainActivity) {
            ((MainActivity) App.activity).getTitleText().setText("发现");
        }
    }

    @Override
    public void setParams(Bundle bundle) {

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

    @OnClick({R.id.imageView, R.id.find_mayun, R.id.kaiyuanruanjian, R.id.saoyisao, R.id.textView2, R.id.yaoyiyao, R.id.textView, R.id.jiaoyin, R.id.fujinchengxuyuan,R.id.xianxiahuodong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                break;
            case R.id.find_mayun:
                break;
            case R.id.kaiyuanruanjian:
                ConfigFragment.getInstance().init().start(KYSoftware.class).build();
                break;
            case R.id.saoyisao:
                Intent intent2=new Intent(getActivity().getApplication().getApplicationContext(),SaoYiSaoActivity.class);
                startActivityForResult(intent2,1028);
                break;
            case R.id.textView2:
                break;
            case R.id.yaoyiyao:
                Intent intent=new Intent(getActivity().getApplication().getApplicationContext(),ShakeActivity .class);
                startActivity(intent);
                break;

            case R.id.textView:
                break;
            case R.id.jiaoyin:
                break;
            case R.id.fujinchengxuyuan:
                break;
            case R.id.xianxiahuodong:
                ConfigFragment.getInstance().init().start(HuoDongFragment.class).build();
               break;
        }
    }

}
