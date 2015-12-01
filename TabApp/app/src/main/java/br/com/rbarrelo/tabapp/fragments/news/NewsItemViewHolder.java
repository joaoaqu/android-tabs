package br.com.rbarrelo.tabapp.fragments.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import br.com.rbarrelo.tabapp.R;

/**
 * Created by rafaelbarrelo on 11/28/15.
 */
public class NewsItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView mItemTextView;

    public NewsItemViewHolder(final View parent, TextView itemTextView) {
        super(parent);
        mItemTextView = itemTextView;
    }

    public static NewsItemViewHolder newInstance(View parent) {
        TextView itemTextView = (TextView) parent.findViewById(R.id.itemTextView);
        return new NewsItemViewHolder(parent, itemTextView);
    }

    public void setItemText(CharSequence text) {
        mItemTextView.setText(text);
    }

}