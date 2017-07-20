package jiyun.com.ipandatv.fragment.pandabroadcast.adapter;

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
import jiyun.com.ipandatv.fragment.pandabroadcast.bean.PandaTebieBean;

/**
 * Created by INS7566 on 2017/7/18.
 */

public class PandaDetailAdapter extends BaseAdapter<PandaTebieBean.VideoBean> {

    public PandaDetailAdapter(Context context,  List<PandaTebieBean.VideoBean> datas) {
        super(context, R.layout.fragment_panda_culture_item, datas);

    }

    @Override
    public void convert(ViewHolder holder, final PandaTebieBean.VideoBean pandaCultureVedioBean) {
        holder.setText(R.id.panda_oculture_item_sp_time, pandaCultureVedioBean.getLen())
                .setText(R.id.panda_culture_item_title, pandaCultureVedioBean.getT());

        ImageView imageView = holder.getView(R.id.panda_culture_item_image);
        Glide.with(context).load(pandaCultureVedioBean.getImg()).into(imageView);

        holder.setOnclickListener(R.id.culture_relat, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoActivity.class);

                intent.putExtra("pid", pandaCultureVedioBean.getVid());
                intent.putExtra("title", pandaCultureVedioBean.getT());
//                MyLog.e("Url", listBean.getUrl() + listBean.getTitle());
                App.activity.startActivity(intent);
            }
        });
    }

}
