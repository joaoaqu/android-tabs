package br.com.rbarrelo.tabapp.model;

import java.util.ArrayList;
import java.util.List;

import br.com.rbarrelo.tabapp.eventbus.LocalEvent;
import de.greenrobot.event.EventBus;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by rafaelbarrelo on 12/5/15.
 */
public class VeiculoHelper {

    private Realm realm;

    public VeiculoHelper() {
        this(Realm.getDefaultInstance());
    }

    public VeiculoHelper(Realm realm){
        this.realm = realm;
    }

    public void closeRealm(){
        if (this.realm != null && !this.realm.isClosed()){
            this.realm.close();
        }
    }

    public boolean existe(String placa){
        Veiculo veiculo = get(placa);
        return  (veiculo != null);
    }

    public Veiculo get(String placa){
        RealmResults<Veiculo> results = this.realm.where(Veiculo.class)
                .equalTo("placa", placa)
                .findAll();

        if (results != null && results.size() > 0){
            return results.first();
        }
        return null;
    }

    public boolean remove(String placa){
        RealmResults<Veiculo> results = this.realm.where(Veiculo.class)
                .equalTo("placa", placa)
                .findAll();

        if (results != null && results.size() > 0){
            this.realm.beginTransaction();
            Veiculo v = results.first();
            v.removeFromRealm();
            this.realm.commitTransaction();

            return  true;
        }

        return false;
    }

    public VeiculoHelper notificaAlteracaoNaLista(){
        RealmResults<Veiculo> dados = this.realm.where(Veiculo.class).findAll();

        if (dados != null){
            dados.sort("placa");
            List<Veiculo> itemList = new ArrayList<>();
            for (Veiculo veiculo : dados) {
                itemList.add(veiculo);
            }

            LocalEvent localEvent = new LocalEvent(itemList);
            EventBus.getDefault().post(localEvent);
        }

        return this;
    }

    public VeiculoHelper insereOuAtualiza(Veiculo veiculo) {
        this.realm.beginTransaction();
        this.realm.copyToRealmOrUpdate(veiculo);
        this.realm.commitTransaction();

        return this;
    }
}
