package com.origicheck.africababa.fragments.stores;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;
import com.origicheck.africababa.controller.intents.Intents;
import com.origicheck.africababa.datamodels.stores.StoresInfo;
import com.origicheck.africababa.sync.worker.SyncExecutorThread;

import java.util.List;

/**
 * Created by victor on 10/1/2015.
 */
public class FragmentStores extends FragmentWrapper implements AdapterView.OnItemClickListener {

    private View mStoresView;
    private ListView mStoresListView;

    private List<StoresInfo> storesInfos;
    private List<String> stores;

    private OnClickStore onClickStore;
    private Receiver receiver;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {
        receiver = new Receiver();
    }

    @NonNull
    @Override
    public String getActivityTitle() {
        return getActivity().getResources().getString(R.string.title_fragment_stores);
    }

    @Override
    public void receiveBundle() {

    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mStoresView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mStoresView);
        getContainer().addView(mStoresView);
    }

    @Override
    public void consumeBundle() {

    }

    @Override
    public void onAttachFragment() {
        onClickStore = (OnClickStore) getActivity();
    }

    @Override
    public void onResumeFragment() {
        getActivity().registerReceiver(receiver, new IntentFilter(Intents.ACTION_SYNCED_STORES));
        getActivity().registerReceiver(receiver, new IntentFilter(Intents.ACTION_SYNCED_LOCATIONS));
    }

    @Override
    public void performPartialSync() {
        if (getUtils().getUserAccountsManager().isExistsUserAccount()) {
            if (getUtils().getPrefsManager().isLoggedIn()) {
                //FORCE A SYNC
                SyncExecutorThread syncExecutor = new SyncExecutorThread(getActivity(), getUtils());
                syncExecutor.syncLocations();
                syncExecutor.syncStores();
            }
        }
    }

    @Override
    public void onPerformPartialSync() {
        populateStores();
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onDestroyFragment() {
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public int getParentLayout() {
        return R.layout.fragment_stores;
    }

    private void initChildViews(@NonNull View storesView) {
        mStoresListView = (ListView) storesView.findViewById(R.id.fragment_stores_listView_stores);
        populateStores();
        mStoresListView.setOnItemClickListener(this);
    }

    private void populateStores() {
        int locationId = getArguments().getInt(Intents.EXTRA_LOCATION, -1);
        storesInfos = getUtils().getTransactionsManager().getStoresInfos(locationId);
        stores = getUtils().getViewCreator().getStoresList(storesInfos);
        mStoresListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stores));

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == mStoresListView) {
            onClickStore.onClickStore(storesInfos.get(position).getStoreId());
        }
    }

    public interface OnClickStore {
        void onClickStore(int storeId);
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intents.ACTION_SYNCED_STORES)
                    || intent.getAction().equals(Intents.ACTION_SYNCED_LOCATIONS)) {
                onPerformPartialSync();
            }
        }
    }
}