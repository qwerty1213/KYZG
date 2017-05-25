package test.bwie.com.example.ins7566.kyzg.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import test.bwie.com.example.ins7566.kyzg.App;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.bean.HuoQuPingLunBean;
import test.bwie.com.example.ins7566.kyzg.fragment.dongtanfragment.Dates;


public class DongTanPingLunAdapter extends BaseAdapter<HuoQuPingLunBean.CommentBean> {
    public DongTanPingLunAdapter(Context context, List<HuoQuPingLunBean.CommentBean> datas) {
        super(context, R.layout.item_dongtanxiangqing_pinglun, datas);
    }

    @Override
    public void convert(ViewHolder holder, HuoQuPingLunBean.CommentBean commentBean) {
        holder.setText(R.id.pinglunlist_content,commentBean.getContent());
        holder.setText(R.id.pinglunList_Name,commentBean.getAuthor());
        String date = Dates.getDate(commentBean.getPubDate());
        holder.setText(R.id.pinglunlist_date,date);
        final ImageView imageView = holder.getView(R.id.pinglunlist_head);
        Glide.with(App.activity).load(commentBean.getPortrait())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
