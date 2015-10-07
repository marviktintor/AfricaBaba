package com.origicheck.africababa.fragments.products.categories;

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
import com.origicheck.africababa.datamodels.products.categories.ProductCategoriesInfo;

import java.util.List;

/**
 * Created by victor on 10/2/2015.
 */
public class FragmentCategories extends FragmentWrapper implements AdapterView.OnItemClickListener {

    public OnProductCategoryClick onProductCategoryClick;
    private View mCategoriesView;
    private ListView mCategoriesListView;
    private List<ProductCategoriesInfo> productCategoriesInfos;
    private List<String> productCategories;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void receiveBundle() {

    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCategoriesView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mCategoriesView);
        getContainer().addView(mCategoriesView);
    }

    @Override
    public void consumeBundle() {

    }


    @Override
    public void onAttachFragment() {
        onProductCategoryClick = (OnProductCategoryClick) getActivity();
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
        return R.layout.fragment_product_categories;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == mCategoriesListView) {
            int productCategory = productCategoriesInfos.get(position).getCategoryId();
            onProductCategoryClick.onProductCategoryClick(productCategory);
        }
    }

    private void initChildViews(View categoriesView) {

        mCategoriesListView = (ListView) categoriesView.findViewById(R.id.fragment_product_categories_listView_categories);
        populateProductCategories();
        mCategoriesListView.setOnItemClickListener(this);

    }

    private void populateProductCategories() {
        productCategoriesInfos = getUtils().getTransactionsManager().getProductCategoriesList();
        productCategories = getUtils().getViewPopulator().getProductCategoriesList(productCategoriesInfos);

        mCategoriesListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, productCategories));
    }


    public interface OnProductCategoryClick {
        void onProductCategoryClick(int productCategory);
    }
}
