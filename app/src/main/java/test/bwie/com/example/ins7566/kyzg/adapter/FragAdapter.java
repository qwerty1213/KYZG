package test.bwie.com.example.ins7566.kyzg.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

import test.bwie.com.example.ins7566.kyzg.base.BaseFragment;


/**
 * Created by Administrator on 2017/4/12.
 */

public class FragAdapter extends FragmentStatePagerAdapter {
    private List<String> listName;
    private List<BaseFragment> fragments;

    public FragAdapter(FragmentManager fm, List<String> listName, List<BaseFragment> fragments) {
        super(fm);
        this.listName = listName;
        this.fragments = fragments;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listName.get(position);
    }
}
