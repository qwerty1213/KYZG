package test.bwie.com.example.ins7566.kyzg.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by INS7566 on 2017/5/9.
 */

public class LunBotuAdapter extends PagerAdapter {
    private List<View> list;

    public LunBotuAdapter(List<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list.isEmpty())
            return 0;
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (container != null) {
            container.removeView(list.get(position % list.size()));
        }
        if (list.size() > 0)
            container.addView(list.get(position % list.size()));
        return list.get(position % list.size());
    }
}

