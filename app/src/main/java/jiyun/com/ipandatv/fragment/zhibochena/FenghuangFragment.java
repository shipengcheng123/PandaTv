package jiyun.com.ipandatv.fragment.zhibochena;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.TotalList.zhibochena.ZhiBoChenaContract;
import jiyun.com.ipandatv.TotalList.zhibochena.ZhiBoChenaPresenter;
import jiyun.com.ipandatv.adapter.ZhiBochenaAdapter;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.model.entity.zhibochena.ChangchengBean;

/**
 * Created by Lenovo on 2017/7/14.
 */
public class FenghuangFragment extends BaseFragment implements ZhiBoChenaContract.View{
    @BindView(R.id.ZhiboChena_ListView)
    ListView mListView;
    Unbinder unbinder;
    private ZhiBochenaAdapter mAdapter;
    private List<ChangchengBean.LiveBean> mList = new ArrayList<>();
    ZhiBoChenaContract.Presenter presenter;
    private ZhiBoChenaPresenter zhiBoChenaPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.zhibochena_listview;
    }

    @Override
    protected void init(View view) {
        mAdapter = new ZhiBochenaAdapter(getContext(),mList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        zhiBoChenaPresenter = new ZhiBoChenaPresenter(this);
        presenter.start();
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

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setChangcheng(ChangchengBean changchengBean) {

    }

    @Override
    public void setTaishan(ChangchengBean changchengBean) {

    }

    @Override
    public void setHuangshan(ChangchengBean changchengBean) {

    }

    @Override
    public void setfenghuanggucheng(ChangchengBean changchengBean) {
        mList.addAll(changchengBean.getLive());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setemeishan(ChangchengBean changchengBean) {

    }

    @Override
    public void setzhangjiajie(ChangchengBean changchengBean) {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setBasePresenter(ZhiBoChenaContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
