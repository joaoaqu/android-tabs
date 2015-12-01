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

import java.util.ArrayList;

import br.com.rbarrelo.tabapp.fragments.FormFragment;
import br.com.rbarrelo.tabapp.fragments.local.LocalLista;
import br.com.rbarrelo.tabapp.fragments.news.NewsLista;
import br.com.rbarrelo.tabapp.util.Commom;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

            tabs.add(getResources().getString(R.string.tabNews));
            tabs.add(getResources().getString(R.string.tabList));
            tabs.add(getResources().getString(R.string.tabForm));
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(Commom.TAG, "getItem: " + position);

            switch (position){
                case 0:
                    return NewsLista.newInstance();
                case 1:
                    return LocalLista.newInstance();
                default: //case 2:
                    return FormFragment.newInstance("A" + position, "B" + position);
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
