package com.origicheck.africababa.fragments.dashboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import com.origicheck.africababa.R;
import com.origicheck.africababa.adapters.dashboard.DashboardAdapter;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;
import com.origicheck.africababa.controller.intents.Intents;
import com.origicheck.africababa.datamodels.dashboard.DashboardInfo;
import com.origicheck.africababa.sync.worker.SyncExecutor;

import java.util.List;
import java.util.Random;

/**
 * Created by victor on 10/1/2015.
 */
public class FragmentDashboard extends FragmentWrapper implements AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {
    private Receiver receiver;

    private View mDashboardView;
    private LinearLayout mSliderView;
    private ListView mDashboardItemsListView;

    private ImageView mSliderImages;

    private List<DashboardInfo> dashboardInfos;

    private OnDashboardListItemClick onDashboardListItemClick;

    private int[] carouselImages = {
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5
    };

    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;

    private RadioButton[] radioButtons;

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

    @Override
    public String getActivityTitle() {
        return getActivity().getResources().getString(R.string.title_fragment_dashboard);
    }
    @Override
    public void consumeBundle() {

    }

    private void initChildViews(View dashboardView) {
        mDashboardItemsListView = (ListView) getParentView().findViewById(R.id.fragment_dashboard_listView_navigation_menu);
        mDashboardItemsListView.setOnItemClickListener(this);

        mSliderView = (LinearLayout) getParentView().findViewById(R.id.fragment_dashboard_linearLayout_carousel_parent);

        mSliderImages = (ImageView) getParentView().findViewById(R.id.fragment_dashboard_imageView_carousel_image);

        radioButton1 = (RadioButton) getParentView().findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) getParentView().findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) getParentView().findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) getParentView().findViewById(R.id.radioButton4);
        radioButton5 = (RadioButton) getParentView().findViewById(R.id.radioButton5);

        radioButton1.setOnCheckedChangeListener(this);
        radioButton2.setOnCheckedChangeListener(this);
        radioButton3.setOnCheckedChangeListener(this);
        radioButton4.setOnCheckedChangeListener(this);
        radioButton5.setOnCheckedChangeListener(this);

        changeCarouselImage(true);

        radioButtons = new RadioButton[]{radioButton1, radioButton2, radioButton3, radioButton4, radioButton5};

        final Button sync = (Button) dashboardView.findViewById(R.id.startSync);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SyncExecutor syncExecutor = new SyncExecutor(getActivity());
                syncExecutor.start();
            }
        });
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
        dashboardInfos = getUtils().getViewPopulator().getDashboardItems();
        mDashboardItemsListView.setAdapter(new DashboardAdapter(getActivity(), R.layout.list_dashboard_items, dashboardInfos));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onDashboardListItemClick.onDashboardListItemClick(parent, view, position, id);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            return;
        }
        if (buttonView == radioButton1) {
            setSliderImage(0);
        }
        if (buttonView == radioButton2) {
            setSliderImage(1);
        }
        if (buttonView == radioButton3) {
            setSliderImage(2);
        }
        if (buttonView == radioButton4) {
            setSliderImage(3);
        }
        if (buttonView == radioButton5) {
            setSliderImage(4);
        }
    }

    private void changeCarouselImage(boolean firstRun) {
        if (firstRun) {
            setSliderImage(-1);
        }
        radioButton1.setChecked(false);
        radioButton2.setChecked(false);
        radioButton3.setChecked(false);
        radioButton4.setChecked(false);
        radioButton5.setChecked(false);

        int i = new Random().nextInt(5);

        if (i == 0) {
            radioButton1.setChecked(true);
        }
        if (i == 1) {
            radioButton2.setChecked(true);
        }
        if (i == 2) {

            radioButton3.setChecked(true);
        }
        if (i == 3) {
            radioButton4.setChecked(true);
        }
        if (i == 4) {
            radioButton5.setChecked(true);
        }

    }

    private void setSliderImage(int i) {
        if (i == -1) {

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.canon5d);
            return;

        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getSliderImages()[i]);
            mSliderImages.setImageBitmap(bitmap);
        }
    }

    private int[] getSliderImages() {
        return carouselImages;
    }

    public interface OnDashboardListItemClick {
        void onDashboardListItemClick(AdapterView<?> parent, View view, int position, long id);
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intents.ACTION_SHOW_NEXT_CAROUSEL_IMAGE)) {
                changeCarouselImage(false);
            }
        }
    }


}
