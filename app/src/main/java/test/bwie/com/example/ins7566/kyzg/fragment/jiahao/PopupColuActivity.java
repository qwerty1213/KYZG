package test.bwie.com.example.ins7566.kyzg.fragment.jiahao;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import test.bwie.com.example.ins7566.kyzg.R;

public class PopupColuActivity extends Activity {

    private ArrayList<String> channels = new ArrayList<>();
    private ArrayList<String> channels_other = new ArrayList<>();
    private DragGridView gridView;
    private DragGridView gridView_other;
    private DragAdapter dragAdapter;
    private DragAdapter other_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_columns);
        initData();
        initDataOther();

        gridView = (DragGridView) findViewById(R.id.gridView_channel);
        gridView_other = (DragGridView) findViewById(R.id.gridView_channel_other);

        gridView.setNumColumns(4);
        dragAdapter = new DragAdapter(this, channels);
        gridView.setAdapter(dragAdapter);

        other_adapter = new DragAdapter(this, channels_other);
        gridView_other.setAdapter(other_adapter);
        gridView_other.setNumColumns(4);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String channel = channels.get(position);
                channels.remove(position);
                channels_other.add(channel);
                dragAdapter.notifyDataSetChanged();
                other_adapter.notifyDataSetChanged();
            }
        });
        gridView_other.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String channel = channels_other.get(position);
                channels_other.remove(position);
                channels.add(channel);
                dragAdapter.notifyDataSetChanged();
                other_adapter.notifyDataSetChanged();
            }
        });
    }

    private void initDataOther() {
        channels_other.add("码云推荐");
        channels_other.add("最新翻译");
        channels_other.add("移动开发");
        channels_other.add("开源硬件");
        channels_other.add("云计算");
        channels_other.add("系统运维");
        channels_other.add("图像多媒体");
        channels_other.add("企业开发");
        channels_other.add("职业生涯");
        channels_other.add("行业杂烩");
    }

    private void initData() {
        channels.add("推荐软件");
        channels.add("技术分享");
        channels.add("高手问答");
        channels.add("开源访谈");
        channels.add("游戏开发");
        channels.add("站务建议");
        channels.add("前端开发");
        channels.add("源创军");
        channels.add("数据库");
        channels.add("编程语言");
        channels.add("服务端开发");
        channels.add("软件工程");
        channels.add("最新博客");
        channels.add("热门博客");
    }
}
