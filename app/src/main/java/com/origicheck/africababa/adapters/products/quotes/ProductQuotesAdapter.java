package com.origicheck.africababa.adapters.products.quotes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.datamodels.products.quotes.ProductQuotesInfo;

import java.util.List;

/**
 * Created by victor on 10/14/2015.
 */
public class ProductQuotesAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ProductQuotesInfo> quotesInfo;


    public ProductQuotesAdapter(Context context, int layout, List<ProductQuotesInfo> quotesInfo) {
        this.context = context;
        this.layout = layout;
        this.quotesInfo = quotesInfo;
    }

    public List<ProductQuotesInfo> getQuotesInfo() {
        return quotesInfo;
    }

    public int getLayout() {
        return layout;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return getQuotesInfo().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        Holder holder = null;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(getLayout(), parent, false);

            holder = new Holder(view);
            view.setTag(holder);
        }

        holder = (Holder) view.getTag();
        holder.createView(getQuotesInfo().get(position));
        return view;
    }

    class Holder {

        private TextView mProduct, mQuantity, mDescription;

        Holder(@NonNull View view) {
            mProduct = (TextView) view.findViewById(R.id.list_quotes_textView_product_name);
            mQuantity = (TextView) view.findViewById(R.id.list_quotes_textView_product_quantity);
            mDescription = (TextView) view.findViewById(R.id.list_quotes_textView_product_description);
        }

        public void createView(@NonNull ProductQuotesInfo productQuotesInfo) {
            mProduct.setText(productQuotesInfo.getProductName());
            mQuantity.setText("#" + productQuotesInfo.getProductQuantity());
            mDescription.setText(productQuotesInfo.getProductDescription());
        }
    }
}
