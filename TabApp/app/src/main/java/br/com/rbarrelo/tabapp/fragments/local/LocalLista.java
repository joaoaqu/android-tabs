package br.com.rbarrelo.tabapp.fragments.local;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import br.com.rbarrelo.tabapp.eventbus.LocalEvent;
import br.com.rbarrelo.tabapp.fragments.ListaFragment;
import br.com.rbarrelo.tabapp.model.VeiculoHelper;
import br.com.rbarrelo.tabapp.util.Commom;

/**
 * Created by rafaelbarrelo on 11/30/15.
 */
public class LocalLista extends ListaFragment {

    private LocalAdapter localAdapter;
    private VeiculoHelper helper;

    @Override
    public void onStart() {
        super.onStart();
        helper = new VeiculoHelper();
        setupRecyclerView();
        Log.i(Commom.TAG, "LocalLista onStart");
    }

    @Override
    public void onStop() {
        helper.closeRealm();
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

        localAdapter = new LocalAdapter(getActivity());
        recyclerView.setAdapter(localAdapter);
    }

    @Override
    protected void setupRecyclerView() {
        helper.notificaAlteracaoNaLista();
    }

    public void onEventMainThread(final LocalEvent event) {
        Log.i(Commom.TAG, "[LocalEvent] onEventMainThread");

        if (event.getVeiculos().size() > 0){
            this.listaPreenchida();
            localAdapter.setVeiculoList(event.getVeiculos());
            localAdapter.notifyDataSetChanged();
            refreshFinished();
        }else{
            this.listaVazia();
        }
    }
}
