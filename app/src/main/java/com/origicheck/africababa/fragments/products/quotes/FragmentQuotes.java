package com.origicheck.africababa.fragments.products.quotes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.adapters.products.quotes.ProductQuotesAdapter;
import com.origicheck.africababa.controller.fragments.FragmentWrapper;
import com.origicheck.africababa.controller.intents.Intents;
import com.origicheck.africababa.datamodels.products.quotes.ProductQuotesInfo;
import com.origicheck.africababa.dialogs.NewQuoteDialog;
import com.origicheck.africababa.sync.worker.SyncExecutorThread;

import java.util.List;

/**
 * Created by victor on 10/13/2015.
 */
public class FragmentQuotes extends FragmentWrapper implements AdapterView.OnItemClickListener, View.OnClickListener, TextWatcher {

    private View mQuotesView;
    private List<ProductQuotesInfo> mQuotesInfos;

    private EditText mSearch;
    private ImageView mCreateQuote;
    private ListView mProductQuotes;

    private NewQuoteDialog mNewQuoteDialog;
    private Receiver receiver;

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {
        receiver = new Receiver();
    }

    @NonNull
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
        getActivity().registerReceiver(receiver, new IntentFilter(Intents.ACTION_SYNCED_QUOTES));
        getActivity().registerReceiver(receiver, new IntentFilter(Intents.ACTION_SYNCED_PRODUCTS));
        getActivity().registerReceiver(receiver, new IntentFilter(Intents.ACTION_SYNCED_PRODUCT_GROUPS));
        getActivity().registerReceiver(receiver, new IntentFilter(Intents.ACTION_SYNCED_CATEGORY));
    }

    @Override
    public void performPartialSync() {
        if (getUtils().getUserAccountsManager().isExistsUserAccount()) {
            if (getUtils().getPrefsManager().isLoggedIn()) {
                //FORCE A SYNC
                SyncExecutorThread syncExecutor = new SyncExecutorThread(getActivity(), getUtils());
                syncExecutor.syncProducts();
                syncExecutor.syncQuotes();
            }
        }
    }

    @Override
    public void onPerformPartialSync() {
        populateQuotes();
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

    private void initChildViews(@NonNull View quotesView) {
        mSearch = (EditText) quotesView.findViewById(R.id.fragment_quotes_editText_search_quote);
        mCreateQuote = (ImageView) quotesView.findViewById(R.id.fragment_quotes_imageView_create_quote);
        mProductQuotes = (ListView) quotesView.findViewById(R.id.fragment_quotes_listView_quotes);


        mCreateQuote.setOnClickListener(this);
        mProductQuotes.setOnItemClickListener(this);
        mSearch.addTextChangedListener(this);

        populateQuotes();
    }

    private void showCreateNewQuoteDialog() {
        mNewQuoteDialog = new NewQuoteDialog(getActivity());
        mNewQuoteDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                populateQuotes();
            }
        });
    }

    private void populateQuotes() {
        mQuotesInfos = getUtils().getTransactionsManager().getProductQuotesInfo(mSearch.getText().toString());
        mProductQuotes.setAdapter(new ProductQuotesAdapter(getActivity(), R.layout.list_quotes, mQuotesInfos));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        populateQuotes();
    }

    private class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intents.ACTION_SYNCED_QUOTES)
                    || intent.getAction().equals(Intents.ACTION_SYNCED_PRODUCTS)
                    || intent.getAction().equals(Intents.ACTION_SYNCED_PRODUCT_GROUPS)
                    || intent.getAction().equals(Intents.ACTION_SYNCED_CATEGORY)) {
                onPerformPartialSync();
            }
        }
    }
}
