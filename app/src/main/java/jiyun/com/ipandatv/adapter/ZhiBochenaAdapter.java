package jiyun.com.ipandatv.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.model.entity.zhibochena.ChangchengBean;
import jiyun.com.ipandatv.view.JCVideoPlayerStandardShowShareButtonAfterFullscreen;

/**
 * Created by Lenovo on 2017/7/14.
 */
public class ZhiBochenaAdapter extends BaseAdapter{

    private Context mContext;
    private List<ChangchengBean.LiveBean> mList;
    private boolean isBocke = false;

    public ZhiBochenaAdapter(Context mContext, List<ChangchengBean.LiveBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    class Holder{
        private JCVideoPlayerStandardShowShareButtonAfterFullscreen jcVideoPlayer;
        private TextView mtitle,mJianjie;
        private ImageButton chebox;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder;
        if(convertView == null) {
            holder = new Holder();
            convertView = View.inflate(mContext, R.layout.zhibochena_item,null);
            holder.jcVideoPlayer = (JCVideoPlayerStandardShowShareButtonAfterFullscreen) convertView.findViewById(R.id.custom_videoplayer_standard_with_share_button);
            holder.mtitle = (TextView) convertView.findViewById(R.id.ZhiboChena_title);
            holder.mJianjie = (TextView) convertView.findViewById(R.id.ZhiboChena_Jianjie);
            holder.chebox = (ImageButton) convertView.findViewById(R.id.ZhiboChena_chebox);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }

        ChangchengBean.LiveBean bean = mList.get(position);
            holder.mtitle.setText(bean.getTitle());
            holder.mJianjie.setText(bean.getBrief());

        holder.jcVideoPlayer.setUrlAndObject(
                "http://2449.vod.myqcloud.com/2449_bfbbfa3cea8f11e5aac3db03cda99974.f20.mp4",
                null,bean.getTitle());
        ImageLoader.getInstance().displayImage(bean.getImage(),
                holder.jcVideoPlayer.ivThumb);



        holder.chebox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isBocke == false) {
                    holder.mJianjie.setVisibility(View.VISIBLE);
                    isBocke =true;
                }else{
                    isBocke=false;
                    holder.mJianjie.setVisibility(View.GONE);
                }
            }
        });

        return convertView;
    }

}
