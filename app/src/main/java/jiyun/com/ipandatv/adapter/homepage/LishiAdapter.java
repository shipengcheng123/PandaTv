package jiyun.com.ipandatv.adapter.homepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.model.db.JiluDao;

/**
 * Created by Lenovo on 2017/7/23.
 */

public class LishiAdapter extends BaseAdapter {

    private Context mContext;
    private List<JiluDao> mList;

    public LishiAdapter(Context mContext, List<JiluDao> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView==null){
            holder=new Holder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.lishijilu_activity,null);
            holder.mImage= (ImageView) convertView.findViewById(R.id.panda_culture_item_image);
            holder.mText= (TextView) convertView.findViewById(R.id.panda_culture_item_title);
            holder.mData = (TextView) convertView.findViewById(R.id.panda_culture_item_time);
            convertView.setTag(holder);
        }else{
            holder= (Holder) convertView.getTag();
        }
        JiluDao jiluDao=mList.get(position);
        holder.mText.setText(jiluDao.getTitle());
        Glide.with(mContext).load(jiluDao.getImageurl()).into(holder.mImage);
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        holder.mData.setText(str);
        return convertView;
    }
    class Holder{
        private ImageView mImage;
        private TextView mText,mData;
    }
}
