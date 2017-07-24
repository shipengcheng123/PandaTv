package jiyun.com.ipandatv.adapter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.activity.VideoActivity;
import jiyun.com.ipandatv.model.db.JiluDao;
import jiyun.com.ipandatv.model.db.MyOpenHelper;
import jiyun.com.ipandatv.model.entity.PandaBroadBean;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * Created by Lenovo on 2017/7/13.
 */
public class BobaoAdapter extends BaseAdapter<PandaBroadBean.ListBean> {
    private Dao<JiluDao,Integer> dao;
    private boolean quchong=false;
    public BobaoAdapter(Context context,List<PandaBroadBean.ListBean> datas) {
        super(context, R.layout.fragment_panda_observe_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, final PandaBroadBean.ListBean pandaBroadBean) {

        holder.setText(R.id.panda_observe_item_title,pandaBroadBean.getTitle());
        holder.setText(R.id.panda_observe_item_sp_time,pandaBroadBean.getVideolength());
//        holder.setText(R.id.panda_observe_item_time,String.valueOf(pandaBroadBean.getFocus_date()));

//        String data1 =  DataUtils.getFormatedDateTime(,pandaBroadBean.getFocus_date());
//        holder.setText(R.id.panda_observe_item_time,data1);
        holder.setText(R.id.panda_observe_item_time, convert((pandaBroadBean.getFocus_date())));


        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.panda_observe_item_image);
        Glide.with(context).load(pandaBroadBean.getPicurl()).into(imageView);

        MyOpenHelper helper = new MyOpenHelper(getContext(), "guankanjilu.db", null, 1);

        try {
            dao = helper.getDao(JiluDao.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        holder.setOnclickListener(R.id.panda_observe_relativeLayout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,VideoActivity.class);
                intent.putExtra("url",pandaBroadBean.getUrl());
                intent.putExtra("title",pandaBroadBean.getTitle());
                intent.putExtra("pid",pandaBroadBean.getGuid());
                intent.putExtra("image",pandaBroadBean.getPicurl());
//                MyLog.e("Url",pandaBroadBean.getUrl()+pandaBroadBean.getTitle());
                App.activity.startActivity(intent);

                try {
                    List<JiluDao> chaxunItem = dao.queryForAll();
                    if(chaxunItem.size() == 0) {
                        JiluDao jiluDao = new JiluDao();
                        jiluDao.setTitle(pandaBroadBean.getTitle());
                        jiluDao.setImageurl( pandaBroadBean.getPicurl());
                        int i = dao.create(jiluDao);
                        Log.e("AAA", "插入了" + i + "条数据");
                    }
                    else {
                        for (int i=0;i<chaxunItem.size();i++){
                            if(pandaBroadBean.getTitle().equals(chaxunItem.get(i).getTitle())) {
                                quchong=true;
                                return;
                            }
                        }
                        if(quchong) {
                            Log.e("tag","相同");
                        }
                        else {
                            JiluDao jiluDao = new JiluDao();
                            jiluDao.setTitle(pandaBroadBean.getTitle());
                            jiluDao.setImageurl( pandaBroadBean.getPicurl());
                            int i = dao.create(jiluDao);
                            Log.e("AAA", "插入了" + i + "条数据");
                            Log.e("tag","添加");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    public String convert(long mill){
        Date date=new Date(mill);
        String strs="";
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            strs=sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

}
