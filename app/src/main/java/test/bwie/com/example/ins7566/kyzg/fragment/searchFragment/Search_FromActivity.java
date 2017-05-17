package test.bwie.com.example.ins7566.kyzg.fragment.searchFragment;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;
import test.bwie.com.example.ins7566.kyzg.db.SearchMyanger;

public class Search_FromActivity extends BaseActivity {
    @BindView(R.id.Search_FromEdit)
    EditText SearchFromEdit;
    @BindView(R.id.Search_FromText)
    TextView SearchFromText;
    @BindView(R.id.Search_FromLayout)
    FrameLayout SearchFromLayout;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private String name;
    private SearchMyanger manger;
    @Override
    protected int getLayoutId() {
        return R.layout.search_fromlayout;
    }

    @Override
    protected void init() {
        manger = new SearchMyanger(getApplicationContext());
        fragmentManager = this.getSupportFragmentManager();
        initTag();
        mShared = getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShared.edit();
        SearchFromEdit.setText(mShared.getString("Name",""));
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.Search_FromText)
    public void onViewClicked() {
        String name = SearchFromEdit.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
        } else {
            mEditor.putString("Name", name);
            manger.insert(name);
            mEditor.commit();
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.Search_FromLayout, new MainSearchFragment());
            transaction.commit();

        }
    }

    private void initTag(){
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.Search_FromLayout,new SearchListFragment());
        transaction.commit();


    }


}
