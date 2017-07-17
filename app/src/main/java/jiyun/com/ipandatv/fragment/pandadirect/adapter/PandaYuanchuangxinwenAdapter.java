package jiyun.com.ipandatv.fragment.pandadirect.adapter;

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
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaYuanchuangxinwenBean;
import jiyun.com.ipandatv.utils.MyLog;

/**
 * Created by INS7566 on 2017/7/14.
 */

public class PandaYuanchuangxinwenAdapter extends BaseAdapter<PandaYuanchuangxinwenBean.VideoBean> {
    public PandaYuanchuangxinwenAdapter(Context context, List<PandaYuanchuangxinwenBean.VideoBean> datas) {
        super(context, R.layout.adapter_jcyk, datas);
    }

    @Override
    public void convert(ViewHolder holder, final PandaYuanchuangxinwenBean.VideoBean bean) {
        holder.setText(R.id.panda_oculture_item_sp_time, bean.getLen())
                .setText(R.id.panda_culture_item_title, bean.getT())
                .setText(R.id.panda_culture_item_time, bean.getPtime());
        ImageView imageView = holder.getView(R.id.panda_culture_item_image);
        Glide.with(context).load(bean.getImg()).into(imageView);
        holder.setOnclickListener(R.id.culture_relat, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,VideoActivity.class);
                intent.putExtra("url",bean.getUrl());
                intent.putExtra("title",bean.getT());
                intent.putExtra("pid",bean.getVid());
                MyLog.e("Url",bean.getUrl()+bean.getT());
                App.activity.startActivity(intent);
            }
        });
    }
}
