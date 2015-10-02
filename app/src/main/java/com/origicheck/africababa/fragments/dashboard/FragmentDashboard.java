package com.origicheck.africababa.fragments.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.adapters.dashboard.DashboardAdapter;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;
import com.origicheck.africababa.datamodels.dashboard.DashboardInfo;

import java.util.List;

/**
 * Created by victor on 10/1/2015.
 */
public class FragmentDashboard extends FragmentWrapper implements AdapterView.OnItemClickListener {

    private View mDashboardView;
    private ImageView mSliderImages;
    private ListView mDashboardItemsListView;

    private List<DashboardInfo> dashboardInfos;

    private OnDashboardListItemClick onDashboardListItemClick;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDashboardView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mDashboardView);
        getContainer().addView(mDashboardView);
    }

    private void initChildViews(View mDashboardView) {
        mDashboardItemsListView = (ListView) getParentView().findViewById(R.id.fragment_dashboard_listView_navigation_menu);
        mDashboardItemsListView.setOnItemClickListener(this);
    }

    private View getParentView() {
        return mDashboardView;
    }

    @Override
    public void onAttachFragment() {
        onDashboardListItemClick = (OnDashboardListItemClick) getActivity();
    }

    @Override
    public void onResumeFragment() {
        populateDashboardItems();

    }


    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onDestroyFragment() {

    }

    @Override
    public int getParentLayout() {
        return R.layout.fragment_dashboard;
    }

    private void populateDashboardItems() {
        dashboardInfos = getUtils().getViewPopulator().getDashboardItems();
        mDashboardItemsListView.setAdapter(new DashboardAdapter(getActivity(), R.layout.list_dashboard_items, dashboardInfos));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onDashboardListItemClick.onDashboardListItemClick(parent, view, position, id);
    }

    public interface OnDashboardListItemClick {
        void onDashboardListItemClick(AdapterView<?> parent, View view, int position, long id);
    }
}
