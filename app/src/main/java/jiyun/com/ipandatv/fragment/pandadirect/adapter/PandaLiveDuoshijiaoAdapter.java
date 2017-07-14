package jiyun.com.ipandatv.fragment.pandadirect.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveDuoshijiaoBean;

/**
 * Created by INS7566 on 2017/7/13.
 */

public class PandaLiveDuoshijiaoAdapter extends BaseAdapter<PandaLiveDuoshijiaoBean.ListBean> {
    public PandaLiveDuoshijiaoAdapter(Context context,  List<PandaLiveDuoshijiaoBean.ListBean> datas) {
        super(context, R.layout.adapter_duoshijiaolive, datas);
    }


    @Override
    public void convert(ViewHolder holder, PandaLiveDuoshijiaoBean.ListBean listBean) {
        ImageView imageView= (ImageView) holder.itemView.findViewById(R.id.duoshijiao_iv);
        Glide.with(App.activity).load(listBean.getImage()).into(imageView);
        holder.setText(R.id.duoshijiao_tv,listBean.getTitle());
    }
}
