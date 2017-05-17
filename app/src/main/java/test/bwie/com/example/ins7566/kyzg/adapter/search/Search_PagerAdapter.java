package test.bwie.com.example.ins7566.kyzg.adapter.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;


/**
 * Created by Administrator on 2017/4/17.
 */

public class Search_PagerAdapter extends FragmentStatePagerAdapter {
    private List<String> ListName;
    private List<BaseFragment> mList;


    public Search_PagerAdapter(FragmentManager fm, List<String> ListName, List<BaseFragment> mList) {
        super(fm);
        this.ListName = ListName;
        this.mList = mList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ListName.get(position);
    }
}
