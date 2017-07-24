package jiyun.com.ipandatv.fragment.shoucahng;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.adapter.homepage.LishiAdapter;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.model.db.JiluDao;
import jiyun.com.ipandatv.model.db.MyOpenHelper;
import jiyun.com.ipandatv.utils.MyLog;

/**
 * Created by Lenovo on 2017/7/24.
 */
public class KandianFragment extends BaseFragment {
    @BindView(R.id.shouchang_kandian_listView)
    ListView mListView;
    Unbinder unbinder;

    private LishiAdapter mAdapter;
    private List<JiluDao> mList = new ArrayList<>();
    private Dao<JiluDao, Integer> dao;

    @Override
    protected int getLayoutId() {
        return R.layout.shouchang_kandian_fragment;
    }

    @Override
    protected void init(View view) {

        MyOpenHelper helper = new MyOpenHelper(getContext(), "shouchang.db", null, 1);

        try {
            dao = helper.getDao(JiluDao.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            List<JiluDao> items = dao.queryForAll();
            MyLog.e("items", items + "");
            mList.addAll(items);
            mAdapter = new LishiAdapter(getContext(), mList);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void loadData() {

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
