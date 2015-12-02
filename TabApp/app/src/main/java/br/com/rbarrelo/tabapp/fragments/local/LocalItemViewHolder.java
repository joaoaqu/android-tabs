package br.com.rbarrelo.tabapp.fragments.local;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.rbarrelo.tabapp.R;

/**
 * Created by rafaelbarrelo on 11/30/15.
 */
public class LocalItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView mItemTextView;

    public LocalItemViewHolder(final View parent, TextView itemTextView) {
        super(parent);
        mItemTextView = itemTextView;
    }

    public static LocalItemViewHolder newInstance(View parent) {
        TextView itemTextView = (TextView) parent.findViewById(R.id.local_title);
        return new LocalItemViewHolder(parent, itemTextView);
    }

    public void setItemText(CharSequence text) {
        mItemTextView.setText(text);
    }

}