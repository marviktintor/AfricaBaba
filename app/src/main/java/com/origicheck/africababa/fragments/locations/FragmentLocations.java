package com.origicheck.africababa.fragments.locations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;
import com.origicheck.africababa.datamodels.locations.LocationsInfo;

import java.util.List;

/**
 * Created by victor on 10/1/2015.
 */
public class FragmentLocations extends FragmentWrapper {

    private ListView mLocationsListView;
    private View mLocationsView;

    private List<String> mLocations;
    private List<LocationsInfo> locationsInfos;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLocationsView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mLocationsView);
        getContainer().addView(mLocationsView);
    }


    @Override
    public void onAttachFragment() {

    }

    @Override
    public void onResumeFragment() {

    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onDestroyFragment() {

    }

    @Override
    public int getParentLayout() {
        return R.layout.fragment_locations;
    }

    private void initChildViews(View locationsView) {
        mLocationsListView = (ListView) locationsView.findViewById(R.id.fragment_locations_listView_locations);

        locationsInfos = getUtils().getTransactionsManager().getLocationInfos();
        mLocations = getUtils().getViewPopulator().getLocationsList(locationsInfos);
        mLocationsListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mLocations));
    }

}
