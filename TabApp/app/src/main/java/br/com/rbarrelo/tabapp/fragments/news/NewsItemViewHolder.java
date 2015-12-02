package br.com.rbarrelo.tabapp.fragments.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.rbarrelo.tabapp.R;

/**
 * Created by rafaelbarrelo on 11/28/15.
 */
public class NewsItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewTitle;
    private final TextView textViewDetalhe;
    private final ImageView imageView;
    private final Context context;
    private final View parent;

    public NewsItemViewHolder(final View parent, TextView title, TextView detalhe, ImageView image) {
        super(parent);
        this.parent = parent;
        this.context = parent.getContext();
        this.textViewTitle = title;
        this.textViewDetalhe = detalhe;
        this.imageView = image;
    }

    public static NewsItemViewHolder newInstance(View parent) {
        TextView itemTitle = (TextView) parent.findViewById(R.id.item_title);
        TextView itemDetalhe = (TextView) parent.findViewById(R.id.item_detalhe);
        ImageView imageView = (ImageView) parent.findViewById(R.id.img_news);
        return new NewsItemViewHolder(parent, itemTitle, itemDetalhe, imageView);
    }

    public void setClick(final Uri uri){
        if (uri != null){
            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                }
            });
        }
    }


    public void setTitle(CharSequence text) {
        textViewTitle.setText(text);
    }

    public void setDetalhe(CharSequence text) {
        textViewDetalhe.setText(text);
    }

    public void setImageView(String url){
        if (url != null && !url.isEmpty()){
            imageView.setVisibility(View.VISIBLE);
            Picasso.with(context)
                    .load(url)
                    .placeholder(android.R.drawable.ic_popup_sync)
                    .error(R.mipmap.ic_launcher)
                    .resize(250, 250)
                    .into(imageView);
        }else{
            imageView.setVisibility(View.GONE);
        }
    }
}