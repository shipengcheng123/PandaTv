package jiyun.com.ipandatv.fragment.pandabroadcast.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.activity.VideoActivity;
import jiyun.com.ipandatv.fragment.pandabroadcast.RollDtialActivity;
import jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture.PandaCultureEntity;
import jiyun.com.ipandatv.utils.MyLog;

/**
 * Created by INS7566 on 2017/7/14.
 */

public class PandaCultureItemAdapter extends BaseAdapter<PandaCultureEntity.ListBean> {
    public PandaCultureItemAdapter(Context context, List<PandaCultureEntity.ListBean> datas) {
        super(context, R.layout.fragment_panda_culture_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final PandaCultureEntity.ListBean listBean) {

        RelativeLayout relativeLayout = holder.getView(R.id.panda_observe_relativeLayout);
/*
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = listBean.;
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });*/

        holder.setText(R.id.panda_oculture_item_sp_time, listBean.getVideoLength())
                .setText(R.id.panda_culture_item_title, listBean.getTitle())
                .setText(R.id.panda_culture_item_time, listBean.getBrief());
        ImageView imageView = holder.getView(R.id.panda_culture_item_image);
        Glide.with(context).load(listBean.getImage()).into(imageView);

        holder.setOnclickListener(R.id.culture_relat, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listBean.getType().toString().equals("2")) {
                    Intent intent2 = new Intent(App.activity, RollDtialActivity.class);
                    intent2.putExtra("url", listBean.getUrl());
                    intent2.putExtra("pid", "84f27011346547c595d78b47a48eb6de");
                    intent2.putExtra("title", listBean.getTitle());
                    MyLog.e("Url", listBean.getUrl() + listBean.getTitle());
                        App.activity.startActivity(intent2);


                } else {
                    Intent intent = new Intent(App.activity, VideoActivity.class);

                    intent.putExtra("url", listBean.getUrl());
                    intent.putExtra("pid", listBean.getId());
                    intent.putExtra("title", listBean.getTitle());
                    MyLog.e("Url", listBean.getUrl() + listBean.getTitle());
                    App.activity.startActivity(intent);


                }
            }
        });
    }
}
