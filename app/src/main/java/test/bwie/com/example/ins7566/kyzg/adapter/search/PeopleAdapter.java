package test.bwie.com.example.ins7566.kyzg.adapter.search;

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
import test.bwie.com.example.ins7566.kyzg.bean.search.SeachPeopleBean;


public class PeopleAdapter extends BaseAdapter<SeachPeopleBean.UserBean> {
    public PeopleAdapter(Context context, List<SeachPeopleBean.UserBean> datas) {
        super(context, R.layout.fragment_search_person_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, SeachPeopleBean.UserBean userBean) {
        holder.setText(R.id.person_item_name,userBean.getName());
        holder.setText(R.id.person_item_address,userBean.getFrom());
        ImageView view = (ImageView) holder.itemView.findViewById(R.id.person_item_image);
        Glide.with(App.activity).load(userBean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(view) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(App.activity.getResources(), resource);
                ciDrawable.setCircular(true);
                view.setImageDrawable(ciDrawable);
            }
        });

    }
}
