package jiyun.com.ipandatv.fragment.Home.tile_right;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseFragment;

/**
 * Created by lx on 2017/7/14.
 */

public class Title_Right extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.panda_person_view;
    }

    @Override
    protected void init(View view) {
        App.mRadiogroup.setVisibility(View.GONE);
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

    @Override
    protected void onShow() {
        super.onShow();
    }

    @Override
    protected void onHidden() {
        super.onHidden();
        App.mRadiogroup.setVisibility(View.GONE);
    }
}
