package test.bwie.com.example.ins7566.kyzg.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private LayoutInflater inflater;
    private List<T> datas;
    private Context context;

    public MyBaseAdapter(Context context, List<T> datas, LayoutInflater inflater) {
        this.context = context;
        this.datas = datas;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        if(datas == null){

            return 0;
        }
        return  datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
    public abstract void convert();

}
