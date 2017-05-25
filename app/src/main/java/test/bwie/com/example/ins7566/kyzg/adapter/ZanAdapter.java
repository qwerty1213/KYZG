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
import test.bwie.com.example.ins7566.kyzg.bean.TweetNewBean;

public class ZanAdapter extends BaseAdapter<TweetNewBean.TweetBean.UserBean> {
    public ZanAdapter(Context context, List<TweetNewBean.TweetBean.UserBean> datas) {
        super(context, R.layout.item_dongtanxiangqing_pinglun ,datas);
    }

    @Override
    public void convert(ViewHolder holder, TweetNewBean.TweetBean.UserBean userBean) {
        holder.setText(R.id.pinglunlist_content,userBean.getUid());
        holder.setText(R.id.pinglunList_Name,userBean.getName());
        final ImageView imageView = holder.getView(R.id.pinglunlist_head);
        Glide.with(App.activity).load(userBean.getPortrait())
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
