package br.com.rbarrelo.tabapp.fragments.local;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.rbarrelo.tabapp.R;

/**
 * Created by rafaelbarrelo on 11/30/15.
 */
public class LocalItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvTitle;
    private final TextView tvSubTitle;
    private final ImageView imgCorLateral;

    public LocalItemViewHolder(final View parent, TextView tvTitle, TextView tvSubTitle, ImageView imgCorLateral) {
        super(parent);
        this.tvTitle = tvTitle;
        this.tvSubTitle = tvSubTitle;
        this.imgCorLateral = imgCorLateral;
    }

    public static LocalItemViewHolder newInstance(View parent) {
        TextView tvTitle = (TextView) parent.findViewById(R.id.local_title);
        TextView tvSub = (TextView) parent.findViewById(R.id.local_subtitle);
        ImageView imgLateral = (ImageView) parent.findViewById(R.id.local_cor_lateral);

        return new LocalItemViewHolder(parent, tvTitle, tvSub, imgLateral);
    }

    public void setTitle(CharSequence text) {
        tvTitle.setText(text);
    }

    public void setSub(CharSequence text) {
        tvSubTitle.setText(text);
    }

    public void setLateral(int cor) {
        imgCorLateral.setBackgroundColor(cor);
    }

}