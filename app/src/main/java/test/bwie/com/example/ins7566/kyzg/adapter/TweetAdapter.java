package test.bwie.com.example.ins7566.kyzg.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import test.bwie.com.example.ins7566.kyzg.App;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.activity.TweetDeatil;
import test.bwie.com.example.ins7566.kyzg.bean.TweetNewBean;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

import static android.os.Build.VERSION_CODES.N;

public class TweetAdapter extends BaseAdapter<TweetNewBean.TweetBean> {
    private String SystemDate;
    private INewsModel modle = new NewsModelImpl();
    private SharedPreferences mShared = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
    ;
    private SharedPreferences.Editor mEditor;
    private String teetId;
    private String uid;
    private String zuozhe;
    private String islike;
    private ImageView zanImageView;

    public TweetAdapter(Context context, List<TweetNewBean.TweetBean> datas) {
        super(context, R.layout.item_zuixindongtan, datas);


    }

    @Override
    public void convert(final ViewHolder holder, final TweetNewBean.TweetBean Bean) {
//      你要找对类型  不要照片来个settext
        holder.setText(R.id.item_newsdongtan_author_body, Bean.getBody());
        holder.setText(R.id.item_newsdongtan_author_name, Bean.getAuthor());
        //如果等于1 代表点赞过 出绿色图片 否则白色图片
        zanImageView = holder.getView(R.id.item_newsdongtan_author_zanImage);
        if ("1".equals(Bean.getIsLike())) {
            zanImageView.setImageResource(R.drawable.ic_thumbup_actived);
        } else {
            zanImageView.setImageResource(R.drawable.ic_thumb_normal);
        }
        // 动弹的id
        teetId = Bean.getId();
        //   用户的id

        //动弹的作者的id
        zuozhe = Bean.getAuthorid();
        mEditor = mShared.edit();
        mEditor.putString("tweet_id", teetId);
        mEditor.commit();

        getListener(holder, Bean);

        //转换时间格式

        getDate(holder, Bean);
        Log.d("TweetAdapter", mShared.getString("sendMsg", ""));

    }

    /**
     * 获取时间，转换时间格式
     *
     * @param holder
     * @param Bean
     */
    private void getDate(final ViewHolder holder, final TweetNewBean.TweetBean Bean) {
        /**
         * 加载图片
         */
        ImageView view = (ImageView) holder.itemView.findViewById(R.id.item_newsdongtan_author_head);

        Glide.with(App.activity).load(Bean.getPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(view) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(App.activity.getResources(), resource);
                ciDrawable.setCircular(true);
                view.setImageDrawable(ciDrawable);
            }
        });
        SystemDate = getDate(System.currentTimeMillis(), "yyyy-MM-dd");//得到当前年月日
        long todayLIngCheng = getMorning(new Date()).getTime(); //今天的凌晨时间
        long newSTime = getDate(Bean.getPubDate(), "yyyy-MM-dd HH:mm:ss");//发布的时间
        long systemLong = new Date(System.currentTimeMillis()).getTime();//当前的时间
        long newsDate = getDate(Bean.getPubDate(), "yyyy-MM-dd");//获取到发布的日期
        long D_Date = getDate(SystemDate, "yyyy-MM-dd");//当前日期
        if (newSTime != 0) {
            long poortime = systemLong - newSTime;//发布的时间距离现在的时间相差多少毫秒
            long poor_s = poortime / 1000;  //现在就是相差多少秒
            if (poor_s < 60) {
                holder.setText(R.id.item_newsdongtan_author_date, poor_s + "秒前");
            } else if (poor_s < 3600) {
                holder.setText(R.id.item_newsdongtan_author_date, poor_s / 60 + "分钟前");
            } else if (newSTime > todayLIngCheng) {
                holder.setText(R.id.item_newsdongtan_author_date, poor_s / 3600 + "小时前");
            } else if (Integer.parseInt(getDate((D_Date - newsDate), "d")) == 1) {
                holder.setText(R.id.item_newsdongtan_author_date, "昨天");
            } else if (Integer.parseInt(getDate((D_Date - newsDate), "d")) == 2) {
                holder.setText(R.id.item_newsdongtan_author_date, "前天");
            } else {
                holder.setText(R.id.item_newsdongtan_author_date, Integer.parseInt(getDate((D_Date - newsDate), "d")) + "天前");
            }

        }
    }

    private void getListener(final ViewHolder holder, final TweetNewBean.TweetBean Bean) {
        //一个数组boolean 用来判断当前页面是否点赞过
        final boolean[] boo = {true};
        //这个是点击赞的监听
        holder.setOnclickListener(R.id.item_newsdongtan_author_zanImage, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //记录每次点击的时候，给islike赋值
                islike = Bean.getIsLike();
                if (mShared.getString("sendMsg", "").isEmpty()) {
                    Toast.makeText(context, "请先登陆", Toast.LENGTH_SHORT).show();
                } else {
                    /*
                    如果点赞过，就取消点赞
                     */
                    if ("1".equals(islike)) {
                        Toast.makeText(context, "你已经点赞过了", Toast.LENGTH_SHORT).show();
                        //取消点赞
                        quxiaozan(holder, Bean, boo);

                        /**
                         * 进行点赞，判读如果没攒过就点赞
                         */

                    } else {
                        //为true的时候就进行点赞，刚开始就是为true
                        if (boo[0]) {
                            modle.DianZan(Bean.getId(), mShared.getString("sendMsg", ""), Bean.getAuthorid(), new MyCallback() {
                                @Override

                                public void onSuccess(String response) {
                                    //给小手赋值，找到id在重新赋值
                                    ImageView image = holder.getView(R.id.item_newsdongtan_author_zanImage);
                                    //刚开始为白色，点赞成功后就让它变色
                                    image.setImageResource(R.drawable.ic_thumbup_actived);
                                    //如果成功之后就加1
                                    int i = Integer.parseInt(Bean.getLikeCount()) + 1;
                                    islike = "1";
                                    Log.d("TweetAdapter点赞额", islike);
                                    //给赞赋值,点赞后就加1
                                    holder.setText(R.id.item_newsdongtan_author_zan, String.valueOf(i));
                                    Log.d("TweetAdapter；；；点赞", response);
                                }


                                @Override
                                public void onError(String error) {
                                    Log.d("TweetAdapter", error);
                                }
                            });

                            boo[0] = false;
                        } else {
                            /**
                             * 在进行判断取消点赞
                             */
                            quxiaozan(holder, Bean, boo);
                        }
                    }
                }
            }

        });


        /**
         * 点击item跳转传值
         */
        holder.setOnclickListener(R.id.dongtan_lin, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TweetDeatil.class);
                intent.putExtra("tweet_body", Bean.getBody());
                intent.putExtra("tweet_name", Bean.getAuthor());
                intent.putExtra("tweet_image", Bean.getPortrait());
                intent.putExtra("tweet_id", Bean.getId());
                intent.putExtra("lists",(ArrayList<? extends Parcelable>) Bean.getLikeList());
                App.activity.startActivity(intent);
            }
        });
    }

    /**
     * 取消赞
     */

    private void quxiaozan(final ViewHolder holder, final TweetNewBean.TweetBean Bean, final boolean[] boo) {
        modle.Unlike(Bean.getId(), mShared.getString("sendMsg", ""), Bean.getAuthorid(), new MyCallback() {
            @Override
            public void onSuccess(String response) {
                ImageView image = holder.getView(R.id.item_newsdongtan_author_zanImage);
                image.setImageResource(R.drawable.ic_thumb_normal);
//                islike = "0";
                boo[0] = true;
                int count = Integer.parseInt(Bean.getLikeCount());
                holder.setText(R.id.item_newsdongtan_author_zan, String.valueOf(count));
                Toast.makeText(context, "取消点赞成功", Toast.LENGTH_SHORT).show();
                Log.d("TweetAdapter请取消点赞", response);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

        //获取今天凌晨时间

    private Date getMorning(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    private String getDate(long time, String geshi) {
        SimpleDateFormat sdf = new SimpleDateFormat(geshi);
        Date date = new Date(time);
        return sdf.format(date);
    }

    private Long getDate(String strTime, String geshi) {
        SimpleDateFormat sdf = new SimpleDateFormat(geshi);
        try {
            return sdf.parse(strTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
