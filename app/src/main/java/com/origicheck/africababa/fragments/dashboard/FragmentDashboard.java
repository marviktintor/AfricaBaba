package com.origicheck.africababa.fragments.dashboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.adapters.dashboard.DashboardAdapter;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;
import com.origicheck.africababa.controller.intents.Intents;
import com.origicheck.africababa.datamodels.dashboard.DashboardInfo;
import com.origicheck.africababa.sync.worker.SyncExecutorThread;

import java.util.List;

/**
 * Created by victor on 10/1/2015.
 */
public class FragmentDashboard extends FragmentWrapper implements AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {
    int currentImagePosition = 0;
    private Receiver receiver;
    private View mDashboardView;
    private LinearLayout mSliderView;
    private ListView mDashboardItemsListView;
    private ImageView mSliderImages;
    private List<DashboardInfo> dashboardInfos;
    private OnDashboardListItemClick onDashboardListItemClick;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {
        receiver = new Receiver();
    }

    @Override
    public void receiveBundle() {

    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDashboardView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mDashboardView);
        getContainer().addView(mDashboardView);
    }

    @NonNull
    @Override
    public String getActivityTitle() {
        return getActivity().getResources().getString(R.string.title_fragment_dashboard);
    }

    @Override
    public void consumeBundle() {

    }

    private void initChildViews(@NonNull View dashboardView) {
        mDashboardItemsListView = (ListView) getParentView().findViewById(R.id.fragment_dashboard_listView_navigation_menu);
        mDashboardItemsListView.setOnItemClickListener(this);

        mSliderView = (LinearLayout) getParentView().findViewById(R.id.fragment_dashboard_linearLayout_carousel_parent);

        mSliderImages = (ImageView) getParentView().findViewById(R.id.fragment_dashboard_imageView_carousel_image);

        changeCarouselImage(true);

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
        getActivity().registerReceiver(receiver, new IntentFilter(Intents.ACTION_SHOW_NEXT_CAROUSEL_IMAGE));
    }

    @Override
    public void performPartialSync() {
        if (getUtils().getUserAccountsManager().isExistsUserAccount()) {
            if (getUtils().getPrefsManager().isLoggedIn()) {
                //FORCE A SYNC
                SyncExecutorThread syncExecutor = new SyncExecutorThread(getActivity(), getUtils());
                syncExecutor.start();
            }
        }
    }

    @Override
    public void onPerformPartialSync() {

    }

    @Override
    public void onPauseFragment() {
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onDestroyFragment() {

    }

    @Override
    public int getParentLayout() {
        return R.layout.fragment_dashboard;
    }

    private void populateDashboardItems() {
        dashboardInfos = getUtils().getViewCreator().getDashboardItems();
        mDashboardItemsListView.setAdapter(new DashboardAdapter(getActivity(), R.layout.list_dashboard_items, dashboardInfos));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onDashboardListItemClick.onDashboardListItemClick(parent, view, position, id);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    private void changeCarouselImage(boolean firstRun) {

        setCurrentImagePosition(getCurrentImagePosition() + 1);

        if (getCurrentImagePosition() >= getSliderImages().length) {
            setCurrentImagePosition(0);
        }

        setSliderImage(getSliderImages()[getCurrentImagePosition()]);
    }

    public int getCurrentImagePosition() {
        return currentImagePosition;
    }

    public void setCurrentImagePosition(int currentImagePosition) {
        this.currentImagePosition = currentImagePosition;
    }

    private void setSliderImage(int fileId) {
        mSliderImages.setImageBitmap(getUtils().getFileBitmap(fileId));
    }

    @NonNull
    private int[] getSliderImages() {
        int[] sliderImages = getUtils().getTransactionsManager().getQuickSaleItemsImagesId();
        if (sliderImages.length > 0) {
            mSliderImages.setVisibility(View.VISIBLE);
        } else {
            mSliderImages.setVisibility(View.INVISIBLE);
        }
        return sliderImages;
    }

    public interface OnDashboardListItemClick {
        void onDashboardListItemClick(AdapterView<?> parent, View view, int position, long id);
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, @NonNull Intent intent) {
            if (intent.getAction().equals(Intents.ACTION_SHOW_NEXT_CAROUSEL_IMAGE)) {
                changeCarouselImage(false);
            }
            if (intent.getAction().equals(Intents.ACTION_SYNCED_FILES) || intent.getAction().equals(Intents.ACTION_SYNCED_QUICK_SALES)) {
                getSliderImages();
            }
        }
    }


}
