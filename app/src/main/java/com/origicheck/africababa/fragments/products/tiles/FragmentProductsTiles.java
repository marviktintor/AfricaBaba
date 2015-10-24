package com.origicheck.africababa.fragments.products.tiles;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.adapters.products.general.ProductsAdapter;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;
import com.origicheck.africababa.controller.intents.Intents;
import com.origicheck.africababa.datamodels.products.advanced.AdvancedProductsInfo;
import com.origicheck.africababa.datamodels.products.simple.SimpleProductsInfo;

import java.util.List;

/**
 * Created by victor on 8/24/2015.
 */
public class FragmentProductsTiles extends FragmentWrapper implements View.OnClickListener, TextWatcher, AdapterView.OnItemClickListener {

    private View mProductView;
    private GridView mProductsGridView;
    private ImageView mProductsViewStyle;
    private AutoCompleteTextView mSearchProducts;

    private List<AdvancedProductsInfo> mAdvancedProductsInfo;
    private List<SimpleProductsInfo> mSimpleProductsInfos;

    private OnProductTilesToggleViewClick onProductToggleViewClick;

    private int storeId = -1;
    private int productGroup = -1;

    private boolean showQuickSaleProducts = false;

    private String activityTitle;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {
        setStoreId();
    }

    @Override
    public String getActivityTitle() {
        return activityTitle;
    }

    @Override
    public void receiveBundle() {
        activityTitle = getString(R.string.title_fragment_products);

        Bundle extras = getArguments();
        if (extras != null) {

            String action = extras.getString(Intents.EXTRA_PRODUCTS_ACTION, Intents.ACTION_SHOW_ALL_PRODUCTS);

            if (action.equals(Intents.ACTION_SHOW_ALL_PRODUCTS)) {
            }
            if (action.equals(Intents.ACTION_SHOW_STORE_PRODUCTS)) {
                setStoreId();
            }
            if (action.equals(Intents.ACTION_SHOW_GROUP_PRODUCTS)) {
                setProductGroup();
            }
            if (action.equals(Intents.ACTION_SHOW_QUICK_SALE_PRODUCTS)) {
                activityTitle = getString(R.string.title_fragment_products_quick_sale);
                setShowQuickSaleProducts(true);
            }
        }
    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mProductView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mProductView);
        getContainer().addView(mProductView);

    }

    @Override
    public void consumeBundle() {
        mSearchProducts.setText(getArguments().getString(Intents.EXTRA_PRODUCTS_SEARCH, ""));
    }


    @Override
    public void onAttachFragment() {
        onProductToggleViewClick = (OnProductTilesToggleViewClick) getActivity();
    }

    @Override
    public void onResumeFragment() {
        populateProducts(mSearchProducts.getText().toString());
    }


    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onDestroyFragment() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        populateProducts(mSearchProducts.getText().toString());
    }

    private void initChildViews(@NonNull View productView) {
        mProductsGridView = (GridView) productView.findViewById(R.id.fragment_products_tiles_gridView_products);
        mProductsGridView.setOnItemClickListener(this);

        mProductsViewStyle = (ImageView) productView.findViewById(R.id.fragment_products_tiles_imageView_toggle_view);
        mProductsViewStyle.setOnClickListener(this);

        mSearchProducts = (AutoCompleteTextView) productView.findViewById(R.id.fragment_products_tiles_autoCompleteTextView_products);
        mSearchProducts.addTextChangedListener(this);

    }

    @Override
    public int getParentLayout() {
        return R.layout.fragment_products_tiles;
    }

    @Override
    public void onClick(View v) {
        if (v == mProductsViewStyle) {
            toggleProductsDisplayStyle();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == mProductsGridView) {
            int productId = mAdvancedProductsInfo.get(position).getProductId();
            getUtils().showOrderedItemsDialog(productId);
        }
    }

    private int getProductsDisplayStyle() {
        return R.layout.list_products_tiles;
    }

    private void populateProducts(@NonNull String product) {
        mAdvancedProductsInfo = getUtils().getTransactionsManager().getAdvancedProductsInfo(product, getStoreId(), getProductGroup(), isShowQuickSaleProducts());
        mSimpleProductsInfos = getUtils().getViewCreator().getSimpleProductsInfo(mAdvancedProductsInfo);
        mProductsGridView.setAdapter(new ProductsAdapter(getActivity(), getProductsDisplayStyle(), mSimpleProductsInfos));
    }

    public void setStoreId() {
        if (getArguments() != null) {
            storeId = getArguments().getInt(Intents.EXTRA_STORE, getStoreId());
        }
    }

    public void setProductGroup() {
        if (getArguments() != null) {
            productGroup = getArguments().getInt(Intents.EXTRA_PRODUCT_GROUP, -1);
        }
    }

    public int getStoreId() {
        return storeId;
    }

    public int getProductGroup() {
        return productGroup;
    }

    public boolean isShowQuickSaleProducts() {
        return showQuickSaleProducts;
    }

    public void setShowQuickSaleProducts(boolean showQuickSaleProducts) {
        this.showQuickSaleProducts = showQuickSaleProducts;
    }

    private void toggleProductsDisplayStyle() {
        int productFragmentLayout = R.layout.fragment_products_details;
        int productDisplayStyle = R.layout.list_products_details;

        Bundle args = getArguments() == null ? new Bundle() : getArguments();
        args.putInt(Intents.EXTRA_PRODUCTS_FRAGMENT_LAYOUT, productFragmentLayout);
        args.putInt(Intents.EXTRA_PRODUCTS_DISPLAY_STYLE, productDisplayStyle);
        args.putInt(Intents.EXTRA_STORE, getStoreId());
        args.putString(Intents.EXTRA_PRODUCTS_SEARCH, mSearchProducts.getText().toString());

        onProductToggleViewClick.onProductTilesToggleViewClick(args);
    }


    public interface OnProductTilesToggleViewClick {
        void onProductTilesToggleViewClick(Bundle extras);
    }
}
