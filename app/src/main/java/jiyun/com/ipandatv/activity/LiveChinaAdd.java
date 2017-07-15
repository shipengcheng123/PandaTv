package jiyun.com.ipandatv.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.adapter.DragAdapter;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.view.DragGridView;

/**
 * Created by lizhuofang on 2017/7/13.
 */
public class LiveChinaAdd extends BaseActivity {

    @BindView(R.id.Fanhui)
    ImageView Fanhui;

    private RelativeLayout relativeLayout;

    private ArrayList<String> channels = new ArrayList<>();
    private ArrayList<String> channels_other = new ArrayList<>();
    private DragGridView gridView;
    private DragGridView gridView_other;
    private DragAdapter dragAdapter;
    private DragAdapter other_adapter;
    private CheckBox button;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_popup_columns;
    }

    @Override
    protected void initView() {
        gridView = (DragGridView) findViewById(R.id.gridView_channel);
        gridView_other = (DragGridView) findViewById(R.id.gridView_channel_other);
        button = (CheckBox) findViewById(R.id.licechina_add_button);
        initDataOther();
    }

    @Override
    public void initData() {
        gridView.setNumColumns(3);
        dragAdapter = new DragAdapter(this, channels);
        gridView.setAdapter(dragAdapter);

        other_adapter = new DragAdapter(this, channels_other);
        gridView_other.setAdapter(other_adapter);
        gridView_other.setNumColumns(3);

        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    button.setText("完成");
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
                } else {
                    button.setText("编辑");
                }
            }
        });
    }

    private void initDataOther() {
        channels.add("八达岭");
        channels.add("泰山");
        channels.add("黄山");
        channels.add("凤凰古城");
        channels.add("峨眉山");
        channels.add("天山");

    }

    @Override
    public void loadData() {
        channels_other.add("张家界");
        channels_other.add("中央电视塔");
        channels_other.add("恒山悬空寺");
        channels_other.add("黄果树");
        channels_other.add("黄龙");
        channels_other.add("武夷山");
        channels_other.add("龙虎山");
        channels_other.add("嵩山少林寺");
        channels_other.add("承德避暑山庄");
        channels_other.add("敦煌月牙泉");
        channels_other.add("都江堰");
        channels_other.add("山海关");
        channels_other.add("水长城");
        channels_other.add("嘉峪关");
        channels_other.add("乌镇");
        channels_other.add("青海湖鸟岛");
        channels_other.add("金丝猴");
        channels_other.add("朱鹮");
        channels_other.add("丹霞山");
        channels_other.add("天涯海角");
        channels_other.add("雪乡");
        channels_other.add("乐山大佛");
        channels_other.add("哈尼梯田");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.Fanhui)
    public void onViewClicked() {
        finish();

    }
}
