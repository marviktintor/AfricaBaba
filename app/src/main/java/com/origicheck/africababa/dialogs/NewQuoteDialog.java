package com.origicheck.africababa.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.origicheck.africababa.R;
import com.origicheck.africababa.controller.utils.Utils;

/**
 * Created by victor on 10/14/2015.
 */
public class NewQuoteDialog extends Dialog implements View.OnClickListener {

    private Utils utils;
    private View mNewQuoteView;

    private EditText mProductName;
    private EditText mProductDescription;
    private EditText mProductQuantity;
    private Button mAddQuote;
    private Button mClearQuote;

    public NewQuoteDialog(@NonNull Context context) {
        super(context);
        utils = new Utils(context);
        mNewQuoteView = getLayoutInflater().inflate(R.layout.dialog_quote_items, null, false);
        initDialogViews(mNewQuoteView);
        setContentView(mNewQuoteView);
        setTitle("Create New Quote");
        show();
    }

    @Override
    public void onClick(View v) {

        if (v == mAddQuote) {
            addNewQuote();
        }
        if (v == mClearQuote) {
            clearNewQuote();
        }
    }

    private void initDialogViews(@NonNull View mNewQuoteView) {
        mProductName = (EditText) mNewQuoteView.findViewById(R.id.dialog_quote_items_editText_product_name);
        mProductDescription = (EditText) mNewQuoteView.findViewById(R.id.dialog_quote_items_editText_product_description);
        mProductQuantity = (EditText) mNewQuoteView.findViewById(R.id.dialog_quote_items_editText_product_quantity);

        mAddQuote = (Button) mNewQuoteView.findViewById(R.id.dialog_quote_items_editText_button_create_quote);
        mClearQuote = (Button) mNewQuoteView.findViewById(R.id.dialog_quote_items_editText_button_clear_quote);

        mAddQuote.setOnClickListener(this);
        mClearQuote.setOnClickListener(this);
    }

    public Utils getUtils() {
        return utils;
    }

    private void addNewQuote() {
        String productQuantity = getUtils().getString(mProductQuantity);
        String productDescription = getUtils().getString(mProductDescription);
        String productName = getUtils().getString(mProductName);

        getUtils().getTransactionsManager().addCreateNewQuote(productName, productDescription, productQuantity);
    }

    private void clearNewQuote() {
        if (getUtils().isEmpty(new EditText[]{mProductDescription, mProductName, mProductQuantity})) {
            dismiss();
        } else {
            getUtils().resetInputs(new EditText[]{mProductDescription, mProductName, mProductQuantity});
        }
    }
}
