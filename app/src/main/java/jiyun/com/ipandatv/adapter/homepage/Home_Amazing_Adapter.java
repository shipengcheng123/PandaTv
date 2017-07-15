package jiyun.com.ipandatv.adapter.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.model.entity.HomePageBean;

/**
 * Created by lx on 2017/7/14.
 */

public class Home_Amazing_Adapter extends BaseAdapter {
    private List<HomePageBean.DataBean.AreaBean.ListscrollBean> mlist;
    private LayoutInflater inflater;
    private Context context;

    public Home_Amazing_Adapter(Context context,List<HomePageBean.DataBean.AreaBean.ListscrollBean> mlist) {
        this.mlist = mlist;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.home_amazing_adapter_item,null);
            viewHolder.amazing_Adapter_Image = (ImageView) convertView.findViewById(R.id.amazing_Adapter_Image);
            viewHolder.amazing_Adapter_Title = (TextView) convertView.findViewById(R.id.amazing_Adapter_Title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HomePageBean.DataBean.AreaBean.ListscrollBean listscrollBean = mlist.get(position);
        viewHolder.amazing_Adapter_Title.setText(listscrollBean.getTitle());
        viewHolder.amazing_Adapter_Image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(listscrollBean.getImage()).into(viewHolder.amazing_Adapter_Image);
        return convertView;
    }
    class ViewHolder{
        ImageView amazing_Adapter_Image;
        TextView amazing_Adapter_Title;
    }
}
