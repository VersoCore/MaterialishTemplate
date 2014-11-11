package com.mattmartz.materialishtemplate;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by matthewmartz on 11/11/14.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ExampleClass exClass;

    public CustomAdapter() {
        exClass = new ExampleClass();
        exClass.initExample();
    }

    public void updateData() {
        this.notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String exOther = exClass.getExampleOther(position);
        holder.itemName.setText(exClass.getExampleName(position));
        holder.itemOther.setText(exOther);
        switch (exOther) {
            case "Recommendation":
                holder.listLayout.setBackgroundResource(R.color.recommendation_color);
                break;
            case "Red":
                holder.listLayout.setBackgroundColor(Color.RED);
                break;
            default:
                holder.listLayout.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return exClass.getSize();
    }

    public String getItemName(int position) {
        return exClass.getExampleName(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout listLayout;
        public TextView itemName;
        public TextView itemOther;

        public ViewHolder(View itemView) {
            super(itemView);
            listLayout = (LinearLayout) itemView.findViewById(R.id.list_item_layout);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemOther = (TextView) itemView.findViewById(R.id.item_other);
        }

    }
}
