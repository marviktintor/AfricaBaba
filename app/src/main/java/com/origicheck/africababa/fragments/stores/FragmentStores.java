package com.origicheck.africababa.fragments.stores;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;
import com.origicheck.africababa.controller.intents.Intents;
import com.origicheck.africababa.datamodels.stores.StoresInfo;

import java.util.List;

/**
 * Created by victor on 10/1/2015.
 */
public class FragmentStores extends FragmentWrapper implements AdapterView.OnItemClickListener {

    private View mStoresView;
    private ListView mStoresListView;

    private List<StoresInfo> storesInfos;
    private List<String> stores;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mStoresView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mStoresView);
        getContainer().addView(mStoresView);
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
        return R.layout.fragment_stores;
    }

    private void initChildViews(View storesView) {
        mStoresListView = (ListView) storesView.findViewById(R.id.fragment_stores_listView_stores);
        populateStores();
        mStoresListView.setOnItemClickListener(this);
    }

    private void populateStores() {
        int locationId = getArguments().getInt(Intents.EXTRA_LOCATION, -1);
        storesInfos = getUtils().getTransactionsManager().getStoresInfos(locationId);
        stores = getUtils().getViewPopulator().getStoresList(storesInfos);
        mStoresListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stores));

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}