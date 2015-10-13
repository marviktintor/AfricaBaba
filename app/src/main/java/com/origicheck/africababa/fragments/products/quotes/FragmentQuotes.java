package com.origicheck.africababa.fragments.products.quotes;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;
import com.origicheck.africababa.datamodels.products.quotes.ProductQuotesInfo;

import java.util.List;

/**
 * Created by victor on 10/13/2015.
 */
public class FragmentQuotes extends FragmentWrapper implements AdapterView.OnItemClickListener, View.OnClickListener {

    private View mQuotesView;
    private List<ProductQuotesInfo> mQuotesInfos;

    private ImageView mCreateQuote;
    private ListView mProductQuotes;

    private View mNewQuoteView;
    private Dialog mNewQuoteDialog;
    private EditText mProductName;
    private EditText mProductDescription;
    private EditText mProductQuantity;
    private Button mAddQuote;
    private Button mClearQuote;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public String getActivityTitle() {
        return getResources().getString(R.string.title_fragment_quotes);
    }

    @Override
    public void receiveBundle() {

    }

    @Nullable
    @Override
    public void onCreateFragmentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mQuotesView = getActivity().getLayoutInflater().inflate(getParentLayout(), container, false);
        initChildViews(mQuotesView);
        getContainer().addView(mQuotesView);
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
        return R.layout.fragment_quotes;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        if (v == mCreateQuote) {
            showCreateNewQuoteDialog();
        }
    }

    private void initChildViews(View quotesView) {

        mCreateQuote = (ImageView) quotesView.findViewById(R.id.fragment_quotes_imageView_create_quote);
        mProductQuotes = (ListView) quotesView.findViewById(R.id.fragment_quotes_listView_quotes);

        mCreateQuote.setOnClickListener(this);
        mProductQuotes.setOnItemClickListener(this);
    }

    private void showCreateNewQuoteDialog() {
        mNewQuoteDialog = new Dialog(getActivity());
        mNewQuoteView = getActivity().getLayoutInflater().inflate(R.layout.dialog_quote_items, null, false);
        initDialogViews(mNewQuoteView);
        mNewQuoteDialog.setContentView(mNewQuoteView);
        mNewQuoteDialog.show();
    }

    private void initDialogViews(View mNewQuoteView) {
        mProductName = (EditText) mNewQuoteView.findViewById(R.id.dialog_quote_items_editText_product_name);
        mProductDescription = (EditText) mNewQuoteView.findViewById(R.id.dialog_quote_items_editText_product_description);
        mProductQuantity = (EditText) mNewQuoteView.findViewById(R.id.dialog_quote_items_editText_product_quantity);

        mAddQuote = (Button) mNewQuoteView.findViewById(R.id.dialog_quote_items_editText_button_create_quote);
        mClearQuote = (Button) mNewQuoteView.findViewById(R.id.dialog_quote_items_editText_button_clear_quote);

        mAddQuote.setOnClickListener(this);
        mClearQuote.setOnClickListener(this);
    }
}
