package com.origicheck.africababa.fragments.products.orders;

import android.content.DialogInterface;
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
import com.origicheck.africababa.datamodels.products.orders.OrderedItemsInfo;
import com.origicheck.africababa.dialogs.DialogConfigOrderedItem;

import java.util.List;

/**
 * Created by victor on 10/8/2015.
 */
public class FragmentOrderedItems extends FragmentWrapper implements AdapterView.OnItemClickListener, DialogInterface.OnDismissListener {

    private List<OrderedItemsInfo> mOrderedItemsInfos;
    private ListView mOrderedItemsListView;
    private View mOrderedItemsView;
    private DialogConfigOrderedItem dialogConfigOrderedItem;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {

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

    }

    @Override
    public int getParentLayout() {
        return R.layout.fragment_ordered_items;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == mOrderedItemsListView) {
            showDialog(position);
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


    private void showDialog(int position) {
        int productId = mOrderedItemsInfos.get(position).getProductId();
        String productName = getUtils().getTransactionsManager().getTblProducts().getProductName(productId);
        int productQuantity = mOrderedItemsInfos.get(position).getQuantity();
        dialogConfigOrderedItem = new DialogConfigOrderedItem(getActivity(), productId, productName, productQuantity);
        dialogConfigOrderedItem.setOnDismissListener(this);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        populateListItems();
    }
}
