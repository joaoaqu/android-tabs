package br.com.rbarrelo.tabapp.eventbus;

import br.com.rbarrelo.tabapp.model.Veiculo;

/**
 * Created by rafaelbarrelo on 12/6/15.
 */
public class UpdateEvent {

    Veiculo veiculo;

    public UpdateEvent(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }
}
