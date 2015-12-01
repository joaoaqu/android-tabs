package br.com.rbarrelo.tabapp.eventbus;

import java.util.List;

/**
 * Created by rafaelbarrelo on 11/30/15.
 */
public class LocalEvent {

    List<String> texto;

    public LocalEvent(List<String> texto) {
        this.texto = texto;
    }

    public List<String> getTexto() {
        return texto;
    }
}
