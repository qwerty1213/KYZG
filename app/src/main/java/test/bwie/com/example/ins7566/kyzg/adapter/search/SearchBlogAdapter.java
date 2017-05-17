package test.bwie.com.example.ins7566.kyzg.adapter.search;

import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.bean.search.SearchBean;

public class SearchBlogAdapter extends BaseAdapter<SearchBean.ResultBean> {

    public SearchBlogAdapter(Context context, List<SearchBean.ResultBean> datas) {
        super(context, R.layout.news_recycler_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, SearchBean.ResultBean resultBean) {
        holder.setText(R.id.contentTitle, resultBean.getTitle());
        holder.setText(R.id.contentBody, resultBean.getDescription());
    }
}
