package br.com.rbarrelo.tabapp.fragments.local;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.rbarrelo.tabapp.eventbus.LocalEvent;
import br.com.rbarrelo.tabapp.fragments.ListaFragment;
import br.com.rbarrelo.tabapp.util.Commom;
import de.greenrobot.event.EventBus;

/**
 * Created by rafaelbarrelo on 11/30/15.
 */
public class LocalLista extends ListaFragment {

    public static LocalLista newInstance() {
        LocalLista localLista = new LocalLista();
        return localLista;
    }

    @Override
    protected void setupRecyclerView() {
        List<String> itemList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            itemList.add("Item " + i);
        }

        LocalEvent localEvent = new LocalEvent(itemList);
        EventBus.getDefault().post(localEvent);
    }

    public void onEventMainThread(final LocalEvent event) {
        Log.i(Commom.TAG, "[LocalEvent] onEventMainThread");
        LocalAdapter localAdapter = new LocalAdapter(event.getTexto());
        recyclerView.setAdapter(localAdapter);
    }

}
