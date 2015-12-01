package br.com.rbarrelo.tabapp.eventbus;

import com.pkmmte.pkrss.Article;

import java.util.List;

/**
 * Created by rafaelbarrelo on 11/30/15.
 */
public class NewsEvent {

    List<Article> articleList;

    public NewsEvent(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<Article> getArticleList() {
        return articleList;
    }
}
