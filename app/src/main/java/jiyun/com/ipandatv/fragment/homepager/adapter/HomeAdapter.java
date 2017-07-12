package jiyun.com.ipandatv.fragment.homepager.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;

/**
 * Created by INS7566 on 2017/7/12.
 */

public class HomeAdapter extends BaseAdapter<HomeBean.TabBean> {
    public HomeAdapter(Context context, List<HomeBean.TabBean> datas) {
        super(context, R.layout.adapter_home, datas);
    }

    @Override
    public void convert(ViewHolder holder, HomeBean.TabBean tabBean) {
        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.home_iv);
        Glide.with(App.activity).load(tabBean.getImage()).into(imageView);
    }
}
