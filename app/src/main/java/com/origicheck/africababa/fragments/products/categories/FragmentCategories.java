package com.origicheck.africababa.fragments.products.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.origicheck.africababa.R;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;

/**
 * Created by victor on 10/2/2015.
 */
public class FragmentCategories extends FragmentWrapper {

    private View mCategoriesView;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCategoriesView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mCategoriesView);
        getContainer().addView(mCategoriesView);
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
        return R.layout.fragment_product_categories;
    }

    private void initChildViews(View mCategoriesView) {
    }
}
