package br.com.rbarrelo.tabapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.pkmmte.pkrss.Article;

import java.util.ArrayList;

import br.com.rbarrelo.tabapp.fragments.form.FormFragment;
import br.com.rbarrelo.tabapp.fragments.local.LocalLista;
import br.com.rbarrelo.tabapp.fragments.news.NewsLista;
import br.com.rbarrelo.tabapp.util.Commom;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private ArrayList<Article> articleArrayList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            articleArrayList = bundle.getParcelableArrayList(NewsLista.ARG_LISTA);
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<String> tabs = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

            tabs.add(getResources().getString(R.string.tab_news));
            tabs.add(getResources().getString(R.string.tab_lista_local));
            tabs.add(getResources().getString(R.string.tab_form));
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(Commom.TAG, "getItem: " + position);

            switch (position){
                case 0:
                    return NewsLista.newInstance(articleArrayList);
                case 1:
                    return LocalLista.newInstance();
                default: //case 2:
                    return FormFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }

}
