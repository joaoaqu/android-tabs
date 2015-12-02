package br.com.rbarrelo.tabapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.pkmmte.pkrss.Article;
import com.pkmmte.pkrss.Callback;
import com.pkmmte.pkrss.PkRSS;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import br.com.rbarrelo.tabapp.fragments.news.NewsLista;
import br.com.rbarrelo.tabapp.util.Commom;

public class SplashActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        frameLayout = (FrameLayout) findViewById(R.id.splash_frame);

        this.hideActionBar();

        this.loadNews();
    }

    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void loadNews(){
        PkRSS.with(this).load(Commom.URL_NEWS).callback(new Callback() {
            @Override
            public void OnPreLoad() {
                Snackbar.make(frameLayout, R.string.carregando, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void OnLoaded(final List<Article> articleList) {
                new Timer().schedule(new TimerTask() {

                    @Override
                    public void run() {
                        finish();

                        Intent intent = new Intent();
                        intent.putParcelableArrayListExtra(NewsLista.ARG_LISTA, (ArrayList<? extends Parcelable>) articleList);
                        intent.setClass(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }, 1000 * Commom.SPLASH_SEG);
            }

            @Override
            public void OnLoadFailed() {
                Snackbar.make(frameLayout, R.string.falha_carregar, Snackbar.LENGTH_LONG).show();
            }
        }).async();
    }
}
