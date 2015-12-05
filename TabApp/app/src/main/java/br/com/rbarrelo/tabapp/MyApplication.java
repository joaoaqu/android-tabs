package br.com.rbarrelo.tabapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by rafaelbarrelo on 12/5/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                                                                .name("rbarrelo.tabapp.relm")
                                                                .deleteRealmIfMigrationNeeded()
                                                                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
