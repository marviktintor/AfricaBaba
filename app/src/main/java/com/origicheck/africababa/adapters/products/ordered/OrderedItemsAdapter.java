package com.origicheck.africababa.adapters.products.ordered;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.controller.utils.Utils;
import com.origicheck.africababa.datamodels.products.orders.OrderedItemsInfo;

import java.util.List;

/**
 * Created by victor on 10/8/2015.
 */
public class OrderedItemsAdapter extends BaseAdapter {

    private Utils utils;

    private Context context;
    private int layout;
    private List<OrderedItemsInfo> orderedItemsInfos;

    public OrderedItemsAdapter(Context context, int layout, List<OrderedItemsInfo> orderedItemsInfos) {
        this.context = context;
        this.layout = layout;
        this.orderedItemsInfos = orderedItemsInfos;

        utils = new Utils(getContext());
    }

    public Context getContext() {
        return context;
    }

    public int getLayout() {
        return layout;
    }

    public List<OrderedItemsInfo> getOrderedItemsInfos() {
        return orderedItemsInfos;
    }

    public Utils getUtils() {
        return utils;
    }

    @Override
    public int getCount() {
        return getOrderedItemsInfos().size();
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

        View orderedItemsView = convertView;
        Holder holder = null;
        if (orderedItemsView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            orderedItemsView = layoutInflater.inflate(getLayout(), parent, false);

            holder = new Holder(orderedItemsView);
            orderedItemsView.setTag(holder);
        }

        holder = (Holder) orderedItemsView.getTag();

        holder.setItemInfo(position);

        return orderedItemsView;
    }

    private class Holder {

        private ImageView mItemAvatar;
        private TextView mItemName;
        private TextView mItemPrice;
        private TextView mItemQuantity;

        Holder(@NonNull View view) {
            mItemAvatar = (ImageView) view.findViewById(R.id.list_ordered_items_imageView_item_avatar);
            mItemName = (TextView) view.findViewById(R.id.list_ordered_items_textView_item_name);
            mItemPrice = (TextView) view.findViewById(R.id.list_ordered_items_textView_item_price);
            mItemQuantity = (TextView) view.findViewById(R.id.list_ordered_items_textView_item_quantity);
        }

        public void setItemInfo(int position) {
            int productId = getOrderedItemsInfos().get(position).getProductId();
            String productName = getUtils().getTransactionsManager().getTblProducts().getProductName(productId);

            mItemName.setText(productName);
            mItemPrice.setText("Ksh. " + getOrderedItemsInfos().get(position).getPrice());
            mItemQuantity.setText("#" + getOrderedItemsInfos().get(position).getQuantity());
            setProductIcon(position);
        }

        public void setProductIcon(int position) {
            int productId = getOrderedItemsInfos().get(position).getProductId();
            int fileId = getUtils().getTransactionsManager().getTblProducts().getFileId(productId);
            mItemAvatar.setImageBitmap(getUtils().getFileBitmap(fileId));
        }
    }
}
