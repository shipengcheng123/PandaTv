package jiyun.com.ipandatv.fragment.pandadirect.adapter;

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
import java.util.List;

import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.activity.VideoActivity;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaDangxiongburangBean;
import jiyun.com.ipandatv.model.db.JiluDao;
import jiyun.com.ipandatv.model.db.MyOpenHelper;
import jiyun.com.ipandatv.utils.MyLog;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * Created by INS7566 on 2017/7/15.
 */

public class PabdaDXBRAdapter extends BaseAdapter<PandaDangxiongburangBean.VideoBean> {
    private Dao<JiluDao,Integer> dao;
    private boolean quchong=false;
    public PabdaDXBRAdapter(Context context, List<PandaDangxiongburangBean.VideoBean> datas) {
        super(context, R.layout.adapter_jcyk, datas);
    }

    @Override
    public void convert(final ViewHolder holder, final PandaDangxiongburangBean.VideoBean bean) {
        holder.setText(R.id.panda_oculture_item_sp_time, bean.getLen())
                .setText(R.id.panda_culture_item_title, bean.getT())
                .setText(R.id.panda_culture_item_time, bean.getPtime());
        ImageView imageView = holder.getView(R.id.panda_culture_item_image);
        Glide.with(context).load(bean.getImg()).into(imageView);
        MyOpenHelper helper = new MyOpenHelper(getContext(), "guankanjilu.db", null, 1);

        try {
            dao = helper.getDao(JiluDao.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        holder.setOnclickListener(R.id.culture_relat, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,VideoActivity.class);
                intent.putExtra("url",bean.getUrl());
                intent.putExtra("title",bean.getT());
                intent.putExtra("pid",bean.getVid());
                MyLog.e("Url",bean.getUrl()+bean.getT());
                App.activity.startActivity(intent);

                try {
                    List<JiluDao> chaxunItem = dao.queryForAll();
                    if(chaxunItem.size() == 0) {
                        JiluDao jiluDao = new JiluDao();
                        jiluDao.setTitle(bean.getT());
                        jiluDao.setImageurl( bean.getImg());
                        int i = dao.create(jiluDao);
                        Log.e("AAA", "插入了" + i + "条数据");
                    }
                    else {
                        for (int i=0;i<chaxunItem.size();i++){
                            if(bean.getT().equals(chaxunItem.get(i).getTitle())) {
                                quchong=true;
                                return;
                            }
                        }
                        if(quchong) {
                            Log.e("tag","相同");
                        }
                        else {
                            JiluDao jiluDao = new JiluDao();
                            jiluDao.setTitle(bean.getT());
                            jiluDao.setImageurl( bean.getImg());
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
}
