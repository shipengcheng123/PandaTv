package jiyun.com.ipandatv.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.activity.VideoActivity;
import jiyun.com.ipandatv.model.entity.PandaBroadBean;
import jiyun.com.ipandatv.utils.MyLog;

/**
 * Created by Lenovo on 2017/7/13.
 */
public class BobaoAdapter extends BaseAdapter<PandaBroadBean.ListBean> {
    public BobaoAdapter(Context context,List<PandaBroadBean.ListBean> datas) {
        super(context, R.layout.fragment_panda_observe_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final PandaBroadBean.ListBean pandaBroadBean) {

        holder.setText(R.id.panda_observe_item_title,pandaBroadBean.getTitle());
        holder.setText(R.id.panda_observe_item_sp_time,pandaBroadBean.getVideolength());
        holder.setText(R.id.panda_observe_item_time,String.valueOf(pandaBroadBean.getFocus_date()));

//        String data1 =  DataUtils.getFormatedDateTime(,pandaBroadBean.getFocus_date());
//        holder.setText(R.id.panda_observe_item_time,data1);
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.panda_observe_item_image);
        Glide.with(context).load(pandaBroadBean.getPicurl()).into(imageView);


        holder.setOnclickListener(R.id.panda_observe_relativeLayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,VideoActivity.class);
                intent.putExtra("url",pandaBroadBean.getUrl());
                intent.putExtra("title",pandaBroadBean.getTitle());
                MyLog.e("Url",pandaBroadBean.getUrl()+pandaBroadBean.getTitle());
                App.activity.startActivity(intent);
            }
        });
    }
}
