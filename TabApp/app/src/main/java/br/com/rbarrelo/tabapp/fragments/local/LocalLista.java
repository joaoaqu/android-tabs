package br.com.rbarrelo.tabapp.fragments.local;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.rbarrelo.tabapp.eventbus.LocalEvent;
import br.com.rbarrelo.tabapp.fragments.ListaFragment;
import br.com.rbarrelo.tabapp.model.Veiculo;
import br.com.rbarrelo.tabapp.util.Commom;
import de.greenrobot.event.EventBus;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by rafaelbarrelo on 11/30/15.
 */
public class LocalLista extends ListaFragment {

    private Realm realm;
    private LocalAdapter localAdapter;

    @Override
    public void onStart() {
        super.onStart();
        realm = Realm.getDefaultInstance();
        setupRecyclerView();
        Log.i(Commom.TAG, "LocalLista onStart");
    }

    @Override
    public void onStop() {
        realm.close();
        Log.i(Commom.TAG, "LocalLista onStop");
        super.onStop();
    }

    public static LocalLista newInstance() {
        LocalLista localLista = new LocalLista();
        return localLista;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        localAdapter = new LocalAdapter();
        recyclerView.setAdapter(localAdapter);
    }

    @Override
    protected void setupRecyclerView() {

        if (realm != null){
            RealmResults<Veiculo> dados = realm.where(Veiculo.class).findAll();
            dados.sort("placa");

            if (dados != null && dados.size() > 0){
                List<Veiculo> itemList = new ArrayList<>();
                for (Veiculo veiculo : dados) {
                    itemList.add(veiculo);
                }

                LocalEvent localEvent = new LocalEvent(itemList);
                EventBus.getDefault().post(localEvent);
            }
        }else{
            Log.i(Commom.TAG, "realm null");
        }

    }

    public void onEventMainThread(final LocalEvent event) {
        Log.i(Commom.TAG, "[LocalEvent] onEventMainThread");
        localAdapter.setVeiculoList(event.getVeiculos());
        localAdapter.notifyDataSetChanged();
        refreshFinished();
    }

}
