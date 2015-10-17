package com.origicheck.africababa.fragments.products.orders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.adapters.products.ordered.OrderedItemsAdapter;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;
import com.origicheck.africababa.controller.intents.Intents;
import com.origicheck.africababa.datamodels.products.orders.OrderedItemsInfo;

import java.util.List;

/**
 * Created by victor on 10/8/2015.
 */
public class FragmentOrderedItems extends FragmentWrapper implements AdapterView.OnItemClickListener {

    private List<OrderedItemsInfo> mOrderedItemsInfos;
    private ListView mOrderedItemsListView;
    private View mOrderedItemsView;
    private BroadcastReceiver orderedItemsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intents.ACTION_NEW_ORDERED_ITEM)) {
                populateListItems();
            }
        }
    };

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {
        getActivity().registerReceiver(orderedItemsReceiver, new IntentFilter(Intents.ACTION_NEW_ORDERED_ITEM));
    }

    @Override
    public String getActivityTitle() {
        return getActivity().getResources().getString(R.string.title_fragment_ordered_items);
    }

    @Override
    public void receiveBundle() {

    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mOrderedItemsView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mOrderedItemsView);
        getContainer().addView(mOrderedItemsView);
    }

    @Override
    public void consumeBundle() {

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
        getActivity().unregisterReceiver(orderedItemsReceiver);
    }

    @Override
    public int getParentLayout() {
        return R.layout.fragment_ordered_items;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == mOrderedItemsListView) {
            int productId = mOrderedItemsInfos.get(position).getProductId();
            showOrderedItemsDialog(productId);
        }
    }

    private void initChildViews(View orderedItemsView) {

        mOrderedItemsListView = (ListView) orderedItemsView.findViewById(R.id.fragment_ordered_items_listView_ordered_items);
        mOrderedItemsListView.setOnItemClickListener(this);

        populateListItems();
    }

    private void populateListItems() {
        mOrderedItemsInfos = getUtils().getTransactionsManager().getOrderedItemsList();
        mOrderedItemsListView.setAdapter(new OrderedItemsAdapter(getActivity(), R.layout.list_ordered_items, mOrderedItemsInfos));
    }

    private void showOrderedItemsDialog(int productId) {
        getUtils().showOrderedItemsDialog(productId);

    }
}
