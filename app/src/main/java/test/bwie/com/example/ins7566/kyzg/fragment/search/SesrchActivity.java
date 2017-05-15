package test.bwie.com.example.ins7566.kyzg.fragment.search;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;
import test.bwie.com.example.ins7566.kyzg.db.SearchMyanger;
import test.bwie.com.example.ins7566.kyzg.fragment.minefragment.MineFragment;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by INS7566 on 2017/5/15.
 */

public class SesrchActivity extends BaseActivity {
    @BindView(R.id.Search_FromEdit)
    EditText SearchFromEdit;
    @BindView(R.id.Search_FromText)
    TextView SearchFromText;
    @BindView(R.id.Search_FromLayout)
    FrameLayout SearchFromLayout;
private SharedPreferences mShared;
    private SharedPreferences.Editor editor;
    private android.support.v4.app.FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction transaction;
    private String name;
    private SearchMyanger manger;
    @Override
    protected int getLayoutId() {
        return R.layout.search_fromlayout;
    }

    @Override
    protected void init() {
        manger=new SearchMyanger(getApplicationContext());
        fragmentManager=this.getSupportFragmentManager();
        mShared=getSharedPreferences("data",MODE_PRIVATE);
        editor=mShared.edit();
        SearchFromEdit.setText(mShared.getString("Name",""));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Search_FromEdit, R.id.Search_FromText, R.id.Search_FromLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Search_FromEdit:
                break;
            case R.id.Search_FromText:
                String name=SearchFromEdit.getText().toString();
                if (name.isEmpty()){
                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    editor.putString("Name",name);
                    manger.insert(name);
                    editor.commit();
                    transaction=fragmentManager.beginTransaction();
                    transaction.replace(R.id.Search_FromLayout,new SearchFragment());
                    transaction.commit();
                }
                break;

            case R.id.Search_FromLayout:
                break;
        }
    }
}
