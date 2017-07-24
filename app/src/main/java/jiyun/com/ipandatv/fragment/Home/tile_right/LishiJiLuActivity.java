package jiyun.com.ipandatv.fragment.Home.tile_right;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.adapter.homepage.LishiAdapter;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.model.db.JiluDao;
import jiyun.com.ipandatv.model.db.MyOpenHelper;
import jiyun.com.ipandatv.utils.MyLog;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * Created by Lenovo on 2017/7/23.
 */
public class LishiJiLuActivity extends BaseActivity {

    @BindView(R.id.lishi_ListView)
    ListView lishiListView;
    @BindView(R.id.Back)
    ImageView Back;
    @BindView(R.id.Bianji)
    TextView Bianji;
    private LishiAdapter mAdapter;
    private List<JiluDao> mList = new ArrayList<>();
    private Dao<JiluDao, Integer> dao;

    @Override
    protected int getLayoutId() {
        return R.layout.lishijili_activity;
    }

    @Override
    protected void initView() {

        MyOpenHelper helper = new MyOpenHelper(getContext(), "guankanjilu.db", null, 1);

        try {
            dao = helper.getDao(JiluDao.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            List<JiluDao> items = dao.queryForAll();
            MyLog.e("items", items + "");
            mList.addAll(items);
            mAdapter = new LishiAdapter(this, mList);
            lishiListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private Boolean chakcox = false;

    @OnClick({R.id.Back, R.id.Bianji})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Back:
                onBackPressed();
                break;
            case R.id.Bianji:

                break;
        }
    }
}
