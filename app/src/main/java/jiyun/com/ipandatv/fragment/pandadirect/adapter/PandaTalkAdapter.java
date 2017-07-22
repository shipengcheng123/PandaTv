package jiyun.com.ipandatv.fragment.pandadirect.adapter;

import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveTalkListBean;

/**
 * Created by INS7566 on 2017/7/13.
 */

public class PandaTalkAdapter extends BaseAdapter<PandaLiveTalkListBean.DataBean.ContentBean> {

    public PandaTalkAdapter(Context context, List<PandaLiveTalkListBean.DataBean.ContentBean> datas) {
        super(context, R.layout.adapter_talk, datas);
    }

    @Override
    public void convert(ViewHolder holder, PandaLiveTalkListBean.DataBean.ContentBean dataBean) {
        holder.setText(R.id.talk_title, dataBean.getAuthor());
        holder.setText(R.id.talk_content, dataBean.getMessage());
        holder.setText(R.id.talk_lou, dataBean.getPid() + "楼");
        holder.setText(R.id.talk_date, dataBean.getDateline());



    }

}
