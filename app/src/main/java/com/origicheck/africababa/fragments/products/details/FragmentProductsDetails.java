package com.origicheck.africababa.fragments.products.details;

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
import android.widget.ImageView;
import android.widget.ListView;

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
public class FragmentProductsDetails extends FragmentWrapper implements View.OnClickListener, TextWatcher, AdapterView.OnItemClickListener {

    private View mProductView;
    private ListView mProductsListView;
    private ImageView mProductsViewStyle;
    private AutoCompleteTextView mSearchProducts;

    private List<AdvancedProductsInfo> mAdvancedProductsInfo;
    private List<SimpleProductsInfo> mSimpleProductsInfos;

    private OnProductDetailsToggleViewClick onProductToggleViewClick;

    private int storeId = -1;
    private int productGroup = -1;

    private boolean showQuickSaleProducts = false;
    private String activityTitle;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public String getActivityTitle() {
        return activityTitle;
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
        onProductToggleViewClick = (OnProductDetailsToggleViewClick) getActivity();
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
    public int getParentLayout() {
        return R.layout.fragment_products_details;
    }

    @Override
    public void receiveBundle() {
        Bundle extras = getArguments();
        activityTitle = getString(R.string.title_fragment_products);

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

    @Override
    public void consumeBundle() {
        setStoreId();
        mSearchProducts.setText(getArguments().getString(Intents.EXTRA_PRODUCTS_SEARCH, ""));
    }

    private void initChildViews(@NonNull View productView) {
        mProductsListView = (ListView) productView.findViewById(R.id.fragment_product_details_listView_products);
        mProductsListView.setOnItemClickListener(this);

        mProductsViewStyle = (ImageView) productView.findViewById(R.id.fragment_product_details_imageView_toggle_view);
        mProductsViewStyle.setOnClickListener(this);

        mSearchProducts = (AutoCompleteTextView) productView.findViewById(R.id.fragment_product_details_autoCompleteTextView_products);
        mSearchProducts.addTextChangedListener(this);


    }


    private int getProductsDisplayStyle() {
        return R.layout.list_products_details;
    }

    private void populateProducts(@NonNull String product) {

        mAdvancedProductsInfo = getUtils().getTransactionsManager().getAdvancedProductsInfo(product, getStoreId(), getProductGroup(), isShowQuickSaleProducts());
        mSimpleProductsInfos = getUtils().getViewCreator().getSimpleProductsInfo(mAdvancedProductsInfo);
        mProductsListView.setAdapter(new ProductsAdapter(getActivity(), getProductsDisplayStyle(), mSimpleProductsInfos));
    }


    @Override
    public void onClick(View v) {
        if (v == mProductsViewStyle) {
            toggleProductsDisplayStyle();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == mProductsListView) {
            int productId = mAdvancedProductsInfo.get(position).getProductId();
            getUtils().showOrderedItemsDialog(productId);
        }
    }
    public void setStoreId() {
        if (getArguments() != null) {
            storeId = getArguments().getInt(Intents.EXTRA_STORE, -1);
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
        int productFragmentLayout = R.layout.fragment_products_tiles;
        int productDisplayStyle = R.layout.list_products_tiles;

        Bundle args = getArguments() == null ? new Bundle() : getArguments();
        args.putInt(Intents.EXTRA_PRODUCTS_FRAGMENT_LAYOUT, productFragmentLayout);
        args.putInt(Intents.EXTRA_PRODUCTS_DISPLAY_STYLE, productDisplayStyle);
        args.putInt(Intents.EXTRA_STORE, getStoreId());
        args.putString(Intents.EXTRA_PRODUCTS_SEARCH, mSearchProducts.getText().toString());

        onProductToggleViewClick.onProductDetailsToggleViewClick(args);
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

    public interface OnProductDetailsToggleViewClick {
        void onProductDetailsToggleViewClick(Bundle extras);
    }
}
