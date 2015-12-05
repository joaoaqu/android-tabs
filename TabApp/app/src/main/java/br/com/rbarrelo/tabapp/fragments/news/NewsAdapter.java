package br.com.rbarrelo.tabapp.fragments.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkmmte.pkrss.Article;

import br.com.rbarrelo.tabapp.R;
import br.com.rbarrelo.tabapp.util.Commom;

import java.util.List;

/**
 * Created by rafaelbarrelo on 11/28/15.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Article> articleList;

    public NewsAdapter() {}

    public void setArticleList(List<Article> itemList) {
        articleList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return NewsItemViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        NewsItemViewHolder holder = (NewsItemViewHolder) viewHolder;

        Article article = articleList.get(position);
        holder.setTitle(article.getTitle());
        holder.setDetalhe(article.getDescription());
        holder.setImageView(article.getImage().toString());
        holder.setClick(article.getSource());
        Log.i(Commom.TAG, "Image URI: " +  article.getImage());
    }

    @Override
    public int getItemCount() {
        return articleList == null ? 0 : articleList.size();
    }

}