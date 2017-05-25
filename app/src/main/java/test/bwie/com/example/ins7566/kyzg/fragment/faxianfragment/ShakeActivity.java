package test.bwie.com.example.ins7566.kyzg.fragment.faxianfragment;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thoughtworks.xstream.XStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.bwie.com.example.ins7566.kyzg.R;
import test.bwie.com.example.ins7566.kyzg.base.BaseActivity;
import test.bwie.com.example.ins7566.kyzg.bean.ShakeNewsModel;
import test.bwie.com.example.ins7566.kyzg.http.INewsModel;
import test.bwie.com.example.ins7566.kyzg.http.NewsModelImpl;
import test.bwie.com.example.ins7566.kyzg.http.UrlUtils.DateUtils;
import test.bwie.com.example.ins7566.kyzg.http.UrlUtils.ThreadUtils;
import test.bwie.com.example.ins7566.kyzg.http.callback.MyCallback;

import static android.R.attr.data;

/**
 * Created by lizhuofang on 2017/5/19.
 */
public class ShakeActivity extends BaseActivity {

    @BindView(R.id.find_shake_return)
    ImageView findShakeReturn;
    @BindView(R.id.find_shake_lin)
    LinearLayout findShakeLin;
    @BindView(R.id.iv_shake)
    ImageView ivShake;
    @BindView(R.id.tv_yyy_choose)
    TextView tvYyyChoose;
    @BindView(R.id.yyy_net_img)
    ImageView yyyNetImg;
    @BindView(R.id.yyy_net_title)
    TextView yyyNetTitle;
    @BindView(R.id.yyy_net_date)
    TextView yyyNetDate;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.yyy_net_news)
    LinearLayout yyyNetNews;
    @BindView(R.id.rb_yyy_lipin)
    RadioButton rbYyyLipin;
    @BindView(R.id.rb_yyy_news)
    RadioButton rbYyyNews;
    @BindView(R.id.rg_yyy_choose)
    RadioGroup rgYyyChoose;
    @BindView(R.id.activity_shake)
    LinearLayout activityShake;
    private SensorManager sensorManager;
    private Sensor sensor;
    private Vibrator vibrator;
    private static final int UPTATE_INTERVAL_TIME = 50;
    private static final int SPEED_SHRESHOLD = 40;//这个值调节灵敏度
    private long lastUpdateTime;
    private float lastX;
    private float lastY;
    private float lastZ;
    private Date parse;
    private String format;
    private INewsModel model;


    @Override
    protected int getLayoutId() {
        return R.layout.find_shake_activity;
    }

    @Override
    protected void init() {
        model=new NewsModelImpl();
        if (yyyNetNews != null) {
            yyyNetNews.setVisibility(View.GONE);
        }

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        if (sensorManager != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if (sensor != null) {
            sensorManager.registerListener(sensorEventListener,
                    sensor,
                    SensorManager.SENSOR_DELAY_GAME);//这里选择感应频率
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.find_shake_return, R.id.rb_yyy_lipin, R.id.rb_yyy_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.find_shake_return:
                finish();
                break;
            case R.id.rb_yyy_lipin:
                yyyNetNews.setVisibility(View.GONE);
                tvYyyChoose.setText("摇一摇抢礼品");
                break;
            case R.id.rb_yyy_news:
                tvYyyChoose.setText("摇一摇获取资讯");
                break;
        }
    }
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            long currentUpdateTime = System.currentTimeMillis();
            long timeInterval = currentUpdateTime - lastUpdateTime;
            if (timeInterval < UPTATE_INTERVAL_TIME) {
                return;
            }
            lastUpdateTime = currentUpdateTime;
            // 传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[0]; // x轴方向的重力加速度，向右为正
            float y = values[1]; // y轴方向的重力加速度，向前为正
            float z = values[2]; // z轴方向的重力加速度，向上为正
            float deltaX = x - lastX;
            float deltaY = y - lastY;
            float deltaZ = z - lastZ;

            lastX = x;
            lastY = y;
            lastZ = z;
            double speed = (Math.sqrt(deltaX * deltaX + deltaY * deltaY
                    + deltaZ * deltaZ) / timeInterval) * 100;
            if (speed >= SPEED_SHRESHOLD) {
                vibrator.vibrate(300);

                if (rbYyyLipin.isChecked()) {

                    tvYyyChoose.setText("抽奖活动已结束，请期待下次活动！");
                } else if (rbYyyNews.isChecked()) {
                    tvYyyChoose.setText("正在获取资讯");
                    getData();
                   ShakeNewsModel biz=new ShakeNewsModel();
                    biz.getAuthor(ShakeActivity.this);
                }


            }
        }
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    public void getData() {
      model.Yaoyiyao(new MyCallback() {
          @Override
          public void onSuccess(String response) {
              ThreadUtils.runOnUIThread(new Runnable() {
                  @Override
                  public void run() {
                      yyyNetNews.setVisibility(View.VISIBLE);
                      tvYyyChoose.setText("摇一摇获取资讯");
                  }
              });
              Log.e("nejvfdskn","0=0==0===0=0===0=0=0=0="+response);
              XStream xStream = new XStream();
              xStream.alias("oschina", ShakeNewsModel.class);
              final ShakeNewsModel o = (ShakeNewsModel) xStream.fromXML(response);
              SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              if(parse!=null){
                  try {
                      parse = sim.parse(o.getPubDate());
                  } catch (ParseException e) {
                      e.printStackTrace();
                  }
                  format = DateUtils.format(parse);

              }
              ThreadUtils.runOnUIThread(new Runnable() {
                  @Override
                  public void run() {
                      Glide.with(ShakeActivity.this).load(o.getImage()).error(R.mipmap.ic_launcher)
                              .placeholder(R.mipmap.ic_launcher).into(yyyNetImg);
                      yyyNetTitle.setText(o.getTitle());
                      yyyNetDate.setText(format);
                  }
              });

          }

          @Override
          public void onError(String error) {

          }
      });
    }
}
