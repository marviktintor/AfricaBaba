package com.origicheck.africababa.fragments.products.tiles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.adapters.products.ProductsAdapter;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;
import com.origicheck.africababa.controller.intents.Intents;
import com.origicheck.africababa.datamodels.products.simple.ProductInfosSimple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 8/24/2015.
 */
public class FragmentProductsTiles extends FragmentWrapper implements View.OnClickListener {

    private View mProductView;
    private GridView mProductsGridView;
    private ImageView mProductsViewStyle;
    private AutoCompleteTextView mSearchProducts;

    private OnProductTilesToggleViewClick onProductToggleViewClick;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mProductView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mProductView);
        getContainer().addView(mProductView);

    }


    @Override
    public void onAttachFragment() {
        onProductToggleViewClick = (OnProductTilesToggleViewClick) getActivity();
    }

    @Override
    public void onResumeFragment() {
        populateProducts();
    }


    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onDestroyFragment() {

    }

    private void initChildViews(View productView) {
        mProductsGridView = (GridView) productView.findViewById(R.id.fragment_products_tiles_gridView_products);
        mProductsViewStyle = (ImageView) productView.findViewById(R.id.fragment_products_tiles_imageView_toggle_view);
        mSearchProducts = (AutoCompleteTextView) productView.findViewById(R.id.fragment_products_tiles_autoCompleteTextView_products);

        mSearchProducts.setText(getArguments().getString(Intents.EXTRA_PRODUCTS_SEARCH, ""));

        mProductsViewStyle.setOnClickListener(this);
    }

    @Override
    public int getParentLayout() {
        return R.layout.fragment_products_tiles;
    }

    private int getProductsDisplayStyle() {
        return R.layout.list_products_tiles;
    }

    private void populateProducts() {
        mProductsGridView.setAdapter(new ProductsAdapter(getActivity(), getProductsDisplayStyle(), getSampleProducts()));
    }

    public List<ProductInfosSimple> getSampleProducts() {
        List<ProductInfosSimple> sampleProducts = new ArrayList<ProductInfosSimple>();
        sampleProducts.add(new ProductInfosSimple(-1, "Biashara Monitor", "Kitamu", 2000));
        sampleProducts.add(new ProductInfosSimple(-1, "Kazi Chap Chap", "BEAC", 1500));
        sampleProducts.add(new ProductInfosSimple(-1, "Africa Baba", "Tubman Rd.", 1000));
        sampleProducts.add(new ProductInfosSimple(-1, "Kazi Finder", "Origi-Check", 1500));


        return sampleProducts;
    }


    @Override
    public void onClick(View v) {
        if (v == mProductsViewStyle) {
            toggleProductsDisplayStyle();
        }
    }

    private void toggleProductsDisplayStyle() {
        int productFragmentLayout = R.layout.fragment_products_details;
        int productDisplayStyle = R.layout.list_products_details;

        Bundle args = new Bundle();
        args.putInt(Intents.EXTRA_PRODUCTS_FRAGMENT_LAYOUT, productFragmentLayout);
        args.putInt(Intents.EXTRA_PRODUCTS_DISPLAY_STYLE, productDisplayStyle);
        args.putString(Intents.EXTRA_PRODUCTS_SEARCH, mSearchProducts.getText().toString());

        onProductToggleViewClick.onProductTilesToggleViewClick(args);
    }

    public interface OnProductTilesToggleViewClick {
        void onProductTilesToggleViewClick(Bundle extras);
    }
}
