package br.com.rbarrelo.tabapp.fragments.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pkmmte.pkrss.Article;
import com.pkmmte.pkrss.Callback;
import com.pkmmte.pkrss.PkRSS;

import java.util.ArrayList;
import java.util.List;

import br.com.rbarrelo.tabapp.R;
import br.com.rbarrelo.tabapp.eventbus.NewsEvent;
import br.com.rbarrelo.tabapp.fragments.ListaFragment;
import br.com.rbarrelo.tabapp.util.Commom;
import de.greenrobot.event.EventBus;

/**
 * Created by rafaelbarrelo on 11/30/15.
 */
public class NewsLista extends ListaFragment {

    public static final String ARG_LISTA = "lista";
    private NewsAdapter newsAdapter;

    public static NewsLista newInstance(ArrayList<Article> articleList) {
        NewsLista newsLista = new NewsLista();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LISTA, articleList);
        newsLista.setArguments(args);
        return newsLista;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsAdapter = new NewsAdapter();
        recyclerView.setAdapter(newsAdapter);

        setupRecyclerView();
    }

    @Override
    protected void setupRecyclerView() {
        if (getArguments() != null) {
            ArrayList<Article> articleArrayList = getArguments().getParcelableArrayList(ARG_LISTA);

            if (articleArrayList != null){
                NewsEvent newsEvent = new NewsEvent(articleArrayList);
                EventBus.getDefault().post(newsEvent);
            }else{
                reloadArticles();
            }
        }else{
            reloadArticles();
        }
    }

    private void reloadArticles(){
        PkRSS.with(getActivity()).load(Commom.URL_NEWS).callback(new Callback() {
            @Override
            public void OnPreLoad() {
                Snackbar.make(getView(), R.string.carregando, Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void OnLoaded(List<Article> articleList) {
                NewsEvent newsEvent = new NewsEvent(articleList);
                EventBus.getDefault().post(newsEvent);
            }

            @Override
            public void OnLoadFailed() {
                Snackbar.make(getView(), R.string.falha_carregar, Snackbar.LENGTH_LONG).show();
            }
        }).async();
    }

    public void onEventMainThread(final NewsEvent event) {
        Log.i(Commom.TAG, "[NewsLista] onEventMainThread");
        newsAdapter.setArticleList(event.getArticleList());
        newsAdapter.notifyDataSetChanged();
        refreshFinished();
    }
}