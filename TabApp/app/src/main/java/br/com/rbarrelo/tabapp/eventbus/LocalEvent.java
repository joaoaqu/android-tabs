package br.com.rbarrelo.tabapp.eventbus;

import java.util.List;

import br.com.rbarrelo.tabapp.model.Veiculo;

/**
 * Created by rafaelbarrelo on 11/30/15.
 */
public class LocalEvent {

    List<Veiculo> veiculos;

    public LocalEvent(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
