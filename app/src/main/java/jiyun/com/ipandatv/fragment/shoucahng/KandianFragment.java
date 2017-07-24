package jiyun.com.ipandatv.fragment.shoucahng;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.activity.VideoActivity;
import jiyun.com.ipandatv.adapter.ShoucangAdapter;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.model.db.MyTwoOpenHelper;
import jiyun.com.ipandatv.model.db.ShouchangDao;
import jiyun.com.ipandatv.utils.MyLog;

/**
 * Created by Lenovo on 2017/7/24.
 */
public class KandianFragment extends BaseFragment {
    @BindView(R.id.shouchang_kandian_listView)
    ListView mListView;
    Unbinder unbinder;

    private ShoucangAdapter mAdapter;
    private List<ShouchangDao> mList = new ArrayList<>();
    private Dao<ShouchangDao, Integer> dao;
    private TextView textView;

    public KandianFragment(TextView textView) {
        this.textView = textView;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.shouchang_kandian_fragment;
    }

    @Override
    protected void init(View view) {

        MyTwoOpenHelper helper = new MyTwoOpenHelper(getContext(), "shouchang.db", null, 1);

        try {
            dao = helper.getDao(ShouchangDao.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            List<ShouchangDao> items = dao.queryForAll();
            MyLog.e("items", items + "");
            mList.addAll(items);
            mAdapter = new ShoucangAdapter(getContext(), mList);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().toString().trim().equals("编辑")){
                    textView.setText("完成");
                    mAdapter.setBb(true);
                    mAdapter.notifyDataSetChanged();
                }else if(textView.getText().toString().trim().equals("完成")){
                    textView.setText("编辑");
                    mAdapter.setBb(false);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });





    }

    @Override
    protected void loadData() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pid = mList.get(position).getPid();
                String title = mList.get(position).getTitle();
                Intent intent = new Intent(getContext(), VideoActivity.class);
                intent.putExtra("pid",pid);
                intent.putExtra("title",title);
                App.activity.startActivity(intent);
            }
        });
    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
