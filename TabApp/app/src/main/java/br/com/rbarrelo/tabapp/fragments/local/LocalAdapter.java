package br.com.rbarrelo.tabapp.fragments.local;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.rbarrelo.tabapp.R;
import br.com.rbarrelo.tabapp.fragments.news.NewsItemViewHolder;

/**
 * Created by rafaelbarrelo on 11/30/15.
 */
public class LocalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mItemList;

    public LocalAdapter(List<String> itemList) {
        mItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.local_item, parent, false);
        return NewsItemViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        NewsItemViewHolder holder = (NewsItemViewHolder) viewHolder;
        String itemText = mItemList.get(position);
        holder.setItemText(itemText);
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }
}
