package br.com.rbarrelo.tabapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rafaelbarrelo on 12/5/15.
 */
public class Veiculo extends RealmObject {

    @PrimaryKey
    private String placa;

    private String marca;
    private String modelo;
    private int cor;

    public String getPlaca() {
        return placa.toUpperCase();
    }

    public void setPlaca(String placa) {
        this.placa = placa.toUpperCase();
    }

    public String getMarca() {
        return marca.toUpperCase();
    }

    public void setMarca(String marca) {
        this.marca = marca.toUpperCase();
    }

    public String getModelo() {
        return modelo.toUpperCase();
    }

    public void setModelo(String modelo) {
        this.modelo = modelo.toUpperCase();
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }
}
