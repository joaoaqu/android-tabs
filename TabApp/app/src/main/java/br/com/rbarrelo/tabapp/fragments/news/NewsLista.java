package br.com.rbarrelo.tabapp.fragments.news;

import android.support.design.widget.Snackbar;
import android.util.Log;

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

    public static NewsLista newInstance() {
        NewsLista newsLista = new NewsLista();
        return newsLista;
    }

    @Override
    protected void setupRecyclerView() {
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
        NewsAdapter newsAdapter = new NewsAdapter(createArticleList(event.getArticleList()));
        recyclerView.setAdapter(newsAdapter);
    }

    private List<String> createArticleList(List<Article> articleList) {
        List<String> itemList = new ArrayList<>();
        for (Article article : articleList) {
            itemList.add(article.getTitle());
        }
        return itemList;
    }

}