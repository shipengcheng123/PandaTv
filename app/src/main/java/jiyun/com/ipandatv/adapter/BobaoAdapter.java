package jiyun.com.ipandatv.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.model.entity.PandaBroadBean;

/**
 * Created by Lenovo on 2017/7/13.
 */
public class BobaoAdapter extends BaseAdapter<PandaBroadBean.ListBean> {
    public BobaoAdapter(Context context,List<PandaBroadBean.ListBean> datas) {
        super(context, R.layout.bobao_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, PandaBroadBean.ListBean pandaBroadBean) {

        holder.setText(R.id.Bobao_item_title,pandaBroadBean.getTitle());
        holder.setText(R.id.Bobao_item_data,pandaBroadBean.getVideolength());
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.Bobao_item_Image);
        Glide.with(context).load(pandaBroadBean.getPicurl()).into(imageView);


    }
}
