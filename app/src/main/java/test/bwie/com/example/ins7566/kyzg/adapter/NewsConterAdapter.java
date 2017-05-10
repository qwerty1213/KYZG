package test.bwie.com.example.ins7566.kyzg.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.activity.WebActivity;
import test.bwie.com.example.ins7566.kyzg.base.MyBaseAdapter;
import test.bwie.com.example.ins7566.kyzg.bean.NewsListBean;

/**
 * Created by INS7566 on 2017/5/9.
 */

public class NewsConterAdapter extends BaseAdapter<NewsListBean.NewsBean> {

    public NewsConterAdapter(Context context, List<NewsListBean.NewsBean> datas) {
        super(context,R.layout.news_recycler_item,datas);
    }

    @Override
    public void convert(ViewHolder holder, final NewsListBean.NewsBean newsBean) {
        holder.setText(R.id.contentTitle,newsBean.getTitle());
        holder.setText(R.id.contentBody,newsBean.getBody());
        holder.setOnclickListener(R.id.Recycle_layout_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, WebActivity.class);
                intent.putExtra("id",newsBean.getId());
                context.startActivity(intent);
            }
        });

    }
}
