package com.origicheck.africababa.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.controller.utils.Utils;

/**
 * Created by victor on 10/8/2015.
 */
public class DialogConfigOrderedItem extends Dialog implements View.OnClickListener, TextWatcher {

    private Utils utils;
    private int itemsQuantity;
    private String itemName;
    private int productId;
    private TextView mExistingItems;
    private EditText mEditItems;
    private Button mAddItems;
    private Button mRemoveItems;

    public DialogConfigOrderedItem(@NonNull Context context, int productId, String title, int itemsQuantity) {
        super(context);

        utils = new Utils(context);
        this.itemsQuantity = itemsQuantity;
        this.itemName = title;
        this.productId = productId;

        setTitle(title);
        View view = getLayoutInflater().inflate(R.layout.dialog_ordered_items, null, false);
        initDialogViews(view);
        setContentView(view);
        show();
    }

    private void initDialogViews(@NonNull View view) {

        mExistingItems = (TextView) view.findViewById(R.id.dialog_ordered_items_textText_items);
        mEditItems = (EditText) view.findViewById(R.id.dialog_ordered_items_editText_items);
        mAddItems = (Button) view.findViewById(R.id.dialog_ordered_items_button_add);
        mRemoveItems = (Button) view.findViewById(R.id.dialog_ordered_items_button_remove);

        mEditItems.addTextChangedListener(this);

        mAddItems.setOnClickListener(this);
        mRemoveItems.setOnClickListener(this);

        mExistingItems.setText(itemsQuantity == 0 ? "You don't have any " + itemName : "You have " + itemsQuantity + " item(s) of " + itemName);

        if (itemsQuantity == 0) {
            mRemoveItems.setVisibility(Button.GONE);
        }
    }


    @Override
    public void onClick(View v) {

        if (mEditItems.getText().toString().equals("")) {
            getUtils().toast("Enter Quantity");
            mEditItems.setTextColor(getContext().getResources().getColor(android.R.color.holo_red_dark));
            return;
        }

        if (v == mAddItems) {
            addItems();
        }
        if (v == mRemoveItems) {
            removeItems();
        }

        dismiss();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mRemoveItems.setEnabled(isItemRemovable());

        if (isItemRemovable()) {
            mEditItems.setTextColor(getContext().getResources().getColor(android.R.color.holo_green_dark));
        } else {
            mEditItems.setTextColor(getContext().getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private boolean isItemRemovable() {
        return mEditItems.getText().length() > 0 && itemsQuantity >= Integer.parseInt(mEditItems.getText().toString());
    }

    private void addItems() {
        getUtils().getTransactionsManager().addOrderedItems(productId, itemsQuantity + Integer.parseInt(mEditItems.getText().toString()));
    }

    private void removeItems() {
        if (!isItemRemovable()) {
            getUtils().toast("Cannot remove item(s)");
        }
        if (itemsQuantity == Integer.parseInt(mEditItems.getText().toString()))
            getUtils().getTransactionsManager().removeAllOrderedItems(productId);
        else
            getUtils().getTransactionsManager().removeOrderedItems(productId, itemsQuantity - Integer.parseInt(mEditItems.getText().toString()));
    }

    public Utils getUtils() {
        return utils;
    }
}
