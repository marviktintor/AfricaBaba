package com.origicheck.africababa.adapters.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.origicheck.africababa.R;
import com.origicheck.africababa.datamodels.dashboard.DashboardInfo;

import java.util.List;

/**
 * Created by victor on 10/1/2015.
 */
public class DashboardAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DashboardInfo> dashboardInfos;

    public DashboardAdapter(Context context, int layout, List<DashboardInfo> dashboardInfos) {
        this.context = context;
        this.layout = layout;
        this.dashboardInfos = dashboardInfos;
    }

    public Context getContext() {
        return context;
    }

    public int getLayout() {
        return layout;
    }

    public List<DashboardInfo> getDashboardInfos() {
        return dashboardInfos;
    }

    @Override
    public int getCount() {
        return getDashboardInfos().size();
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
        Holder holder = null;
        View dashboardView = convertView;
        String title = null;
        String description = null;

        if (dashboardView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dashboardView = layoutInflater.inflate(getLayout(), parent, false);

            holder = new Holder(dashboardView);
            dashboardView.setTag(holder);
        }

        holder = (Holder) dashboardView.getTag();

        description = getDashboardInfos().get(position).getItemDescription();
        holder.setItemDescription(description);

        title = getDashboardInfos().get(position).getItemTitle();
        holder.setItemTitle(title);

        return dashboardView;
    }

    private class Holder {

        private TextView mItemTitle;
        private TextView mItemDescription;

        Holder(View view) {
            mItemTitle = (TextView) view.findViewById(R.id.list_dashboard_items_textView_navigation_item_title);
            mItemDescription = (TextView) view.findViewById(R.id.list_dashboard_items_textView_navigation_item_description);
        }

        void setItemTitle(String title) {
            mItemTitle.setText(title);
        }

        void setItemDescription(String description) {
            mItemDescription.setText(description);
        }
    }
}
