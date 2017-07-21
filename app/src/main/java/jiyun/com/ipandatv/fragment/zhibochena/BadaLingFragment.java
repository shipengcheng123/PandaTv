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
import jiyun.com.ipandatv.adapter.ZhiBochenaAdapter;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.model.entity.zhibochena.ChangchengBean;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * Created by Lenovo on 2017/7/14.
 */
public class BadaLingFragment extends BaseFragment implements BadaLingFragmentCotract.View{

    @BindView(R.id.ZhiboChena_ListView)
    ListView mListView;
    Unbinder unbinder;
    private ZhiBochenaAdapter mAdapter;
    private BadaLingFragmentPresenter presenter;

    private List<ChangchengBean.LiveBean> mList = new ArrayList<>();
    private Bundle bundle;
    private ACache cache;
    @Override
    protected int getLayoutId() {
        return R.layout.zhibochena_listview;
    }

    @Override
    protected void init(View view) {

        new BadaLingFragmentPresenter(this);

        String url = bundle.getString("url");
        if(url!=null) {
            presenter.setUrl(url);
        }

        mAdapter = new ZhiBochenaAdapter(getContext(),mList);
        mListView.setAdapter(mAdapter);

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void setParams(Bundle bundle) {
        this.bundle=bundle;

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
    public void getManager(ChangchengBean changchengBean) {


        mList.addAll(changchengBean.getLive());
        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void showMessage(String msg) {
        cache=ACache.get(getContext());
        ChangchengBean list = (ChangchengBean)cache.getAsObject("ChangchengBean");
        mList.addAll(list.getLive());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setBasePresenter(BadaLingFragmentCotract.Presenter presenter) {
        this.presenter= (BadaLingFragmentPresenter) presenter;
    }
}
