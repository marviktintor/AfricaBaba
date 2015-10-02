package com.origicheck.africababa.fragments.products.groups;

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
import com.origicheck.africababa.datamodels.products.groups.ProductGroupsInfo;

import java.util.List;

/**
 * Created by victor on 10/2/2015.
 */
public class FragmentProductGroups extends FragmentWrapper implements AdapterView.OnItemClickListener {

    private View mProductGroupsView;
    private ListView mProductsGroupListView;

    private List<ProductGroupsInfo> productGroupsInfos;
    private List<String> productGroups;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mProductGroupsView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mProductGroupsView);
        getContainer().addView(mProductGroupsView);
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
        return R.layout.fragment_product_groups;
    }

    private void initChildViews(View productGroupsView) {
        mProductsGroupListView = (ListView) productGroupsView.findViewById(R.id.fragment_product_groups_listView_groups);
        populateProductGroups();
        mProductsGroupListView.setOnItemClickListener(this);
    }

    private void populateProductGroups() {
        int productCategory = getArguments().getInt(Intents.EXTRA_PRODUCT_CATEGORY, -1);
        productGroupsInfos = getUtils().getTransactionsManager().getProductGroupsInfos(productCategory);
        productGroups = getUtils().getViewPopulator().getProductGroupsList(productGroupsInfos);
        mProductsGroupListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, productGroups));

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
