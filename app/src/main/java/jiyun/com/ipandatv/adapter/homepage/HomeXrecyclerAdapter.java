package jiyun.com.ipandatv.adapter.homepage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.model.entity.HomePageBean;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class HomeXrecyclerAdapter extends RecyclerView.Adapter implements ViewPager.OnPageChangeListener {
    private ArrayList<HomePageBean> list;
    private Context context;
    private Timer timer;
    private ArrayList<HomePageBean.DataBean.AreaBean.ListscrollBean> wonderful_list=new ArrayList<>();//精彩直播
    private ArrayList<View> views=new ArrayList<>();//轮播视图
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 888:
                    viewHolder.viewPager.setCurrentItem(viewHolder.viewPager.getCurrentItem()+1);
                    break;
            }
        }
    };
    public HomeXrecyclerAdapter(ArrayList<HomePageBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    ViewHolder viewHolder;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.home_fragment_xrecycler_item,null);
        viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder= (ViewHolder) holder;
        initviewpager();

    }
    @Override
    public int getItemCount() {
        return 1;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        int newPosition = position % views.size();
        for (int i = 0; i < list.get(0).getData().getBigImg().size(); i++) {
            if (i == newPosition) {
                // 就将i对应的点设置为选中状态，其他的点都设置成未选中状态
                viewHolder.point_ratio.getChildAt(i).setBackgroundResource(
                        R.drawable.point_on);
            } else {
                viewHolder.point_ratio.getChildAt(i).setBackgroundResource(
                        R.drawable.point_off);
            }
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ViewPager viewPager;
        LinearLayout point_ratio;
        public ViewHolder(View itemView) {
            super(itemView);

            viewPager= (ViewPager) itemView.findViewById(R.id.home_viewpager);
            point_ratio= (LinearLayout) itemView.findViewById(R.id.point_ratio);

        }
    }












    private void initviewpager() {

        Home_viewpager_Adapter home_viewpager_adapter =new Home_viewpager_Adapter(views);
        viewHolder.viewPager.setAdapter(home_viewpager_adapter);
        viewHolder.viewPager.setOnPageChangeListener(this);
        for (int i=0;i<list.get(0).getData().getBigImg().size();i++){
            View point_item =LayoutInflater.from(context).inflate(R.layout.point_item,null);
            ImageView img = (ImageView) point_item.findViewById(R.id.point_img);
            TextView text= (TextView) point_item.findViewById(R.id.home_point_item_text);
            text.setText(list.get(0).getData().getBigImg().get(i).getTitle());
            Glide.with(context).load(list.get(0).getData().getBigImg().get(i).getImage()).into(img);
            views.add(point_item);
            View view_point=new View(context);
            view_point.setBackgroundResource(R.drawable.rotation_point_black);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20, 20);
            lp.setMargins(20, 0, 0, 0);
            viewHolder.point_ratio.addView(view_point, lp);
        }
        viewHolder.viewPager.setCurrentItem(10000);
        timer = new Timer();
        timer.schedule(task, 4000, 4000);
    }
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            handler.sendEmptyMessage(888);
        }
    };
}
