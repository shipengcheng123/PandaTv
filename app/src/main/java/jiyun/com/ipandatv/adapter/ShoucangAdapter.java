package jiyun.com.ipandatv.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.model.db.ShouchangDao;

/**
 * Created by Lenovo on 2017/7/23.
 */

public class ShoucangAdapter extends BaseAdapter {

    private Context mContext;
    private List<ShouchangDao> mList;
    private boolean bb=false;

    public ShoucangAdapter(Context mContext, List<ShouchangDao> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public boolean isBb() {
        return bb;
    }

    public void setBb(boolean bb) {
        this.bb = bb;
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
            holder.radioButton= (CheckBox) convertView.findViewById(R.id.Bianji_anniu);
            convertView.setTag(holder);
        }else{
            holder= (Holder) convertView.getTag();
        }
        ShouchangDao jiluDao=mList.get(position);
        holder.mText.setText(jiluDao.getTitle());
        Glide.with(mContext).load(jiluDao.getImageurl()).into(holder.mImage);
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        holder.mData.setText(str);
        Log.e("TAG",isBb()+"");
        if(isBb()){
            holder.radioButton.setVisibility(View.VISIBLE);

        }else{
            holder.radioButton.setVisibility(View.GONE);

        }
        return convertView;
    }
    class Holder{
        private ImageView mImage;
        private TextView mText,mData;
        private CheckBox radioButton;
    }
}
