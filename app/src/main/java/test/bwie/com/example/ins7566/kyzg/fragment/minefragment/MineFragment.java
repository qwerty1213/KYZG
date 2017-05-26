package test.bwie.com.example.ins7566.kyzg.fragment.minefragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;

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
import test.bwie.com.example.ins7566.kyzg.fragment.dongtanfragment.MyDongTan;
import test.bwie.com.example.ins7566.kyzg.http.UrlUtils.QRCodeUtil;
import test.bwie.com.example.ins7566.kyzg.http.UrlUtils.ThreadUtils;

/**
 * Created by INS7566 on 2017/5/11.
 */

public class MineFragment extends BaseFragment {


    @BindView(R.id.Mine_Shezhi)
    ImageView MineShezhi;
    @BindView(R.id.MinImageView)
    ImageView MinImageView;
    @BindView(R.id.Mine_TextView)
    TextView MineTextView;
    @BindView(R.id.Mine_Tweet)
    TextView MineTweet;
    @BindView(R.id.Mine_ShouCang)
    TextView MineShouCang;
    @BindView(R.id.Mine_Guanzhu)
    TextView MineGuanzhu;
    @BindView(R.id.Mine_Fensi)
    TextView MineFensi;
    @BindView(R.id.MineLinearlayout)
    LinearLayout MineLinearlayout;
    @BindView(R.id.message)
    ImageView message;
    @BindView(R.id.blog)
    ImageView blog;
    @BindView(R.id.event)
    ImageView event;
    @BindView(R.id.question)
    ImageView question;
    @BindView(R.id.team)
    ImageView team;
    @BindView(R.id.mine_erweima)
    ImageView mineErweima;
    Unbinder unbinder;
    private SharedPreferences mShared;
    private SharedPreferences.Editor meditor;
    private String uid;

    @Override
    protected int layoutId() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView(View view) {
        mShared = getActivity().getSharedPreferences("data", Context.MODE_APPEND);
        meditor = mShared.edit();
        uid = mShared.getString("sendMsg", "");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        getHead();
    }

    private void getHead() {
        String name = mShared.getString("userName", "");
        String port = mShared.getString("port", "");
        if (!name.equals("")) {
            MineTextView.setText(name);
            Glide.with(getActivity()).load(port).asBitmap().centerCrop().into(new BitmapImageViewTarget(MinImageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    ciDrawable.setCircular(true);
                    MinImageView.setImageDrawable(ciDrawable);
                }
            });
        } else {
            MinImageView.setImageResource(R.drawable.ic_nav_my_normal);
        }
    }

    @Override
    protected void onHiddn() {
        unTitleBar();

    }

    @Override
    protected void show() {
        MineLinearlayout.setVisibility(View.VISIBLE);
        getHead();
        unTitleBar();

    }

    @Override
    protected void unTitleBar() {
        if (App.activity instanceof MainActivity) {
            ((MainActivity) App.activity).getMainTitleBar().setVisibility(View.GONE);
            ((MainActivity) App.activity).getMainRadioGroup().setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @OnClick({R.id.Mine_Shezhi, R.id.MinImageView, R.id.Mine_Tweet, R.id.Mine_ShouCang, R.id.Mine_Guanzhu, R.id.Mine_Fensi,R.id.mine_erweima})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Mine_Shezhi:
                ConfigFragment.getInstance().init().start(ShezhiFragment.class).build();
                break;
            case R.id.MinImageView:
                if (mShared.getString("sendMsg", "").isEmpty()) {
                    Intent intent = new Intent(getActivity().getApplication().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "你已经登录过了", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Mine_Tweet:
                ConfigFragment.getInstance().init().start(MyDongTan.class).build();
                break;
            case R.id.Mine_ShouCang:
                break;
            case R.id.Mine_Guanzhu:
                ConfigFragment.getInstance().init().start(GuanZhuFragment.class).build();
                break;
            case R.id.Mine_Fensi:
                ConfigFragment.getInstance().init().start(FenSiFragment.class).build();
                break;
            case R.id.mine_erweima:
                createErweima();
                break;
        }
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
    private void createErweima() {

        final ImageView iv = new ImageView(getContext());

        final String filePath = getFileRoot(getContext()) + File.separator
                + "qr_" + System.currentTimeMillis() + ".jpg";
        final boolean success = QRCodeUtil.createQRImage("哈哈哈a", 800, 800, null, filePath);

        ThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (success) {
                    iv.setImageBitmap(BitmapFactory.decodeFile(filePath));
                } else {
//                    Log.e("MineFragment...", "run: 生成失败");
                }
            }
        });
        PopupWindow pop = new PopupWindow(iv, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setContentView(iv);
        pop.setBackgroundDrawable(new ColorDrawable());
        pop.setOutsideTouchable(true);
        pop.showAtLocation(mineErweima, Gravity.CENTER, 0, 0);

    }
    //文件存储根目录
    private String getFileRoot(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File external = context.getExternalFilesDir(null);
            if (external != null) {
                return external.getAbsolutePath();
            }
        }

        return context.getFilesDir().getAbsolutePath();
    }

}
