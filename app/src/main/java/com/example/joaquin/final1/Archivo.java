package com.example.joaquin.final1;

/**
 * Created by Joaquin on 16/12/2017.
 */

public class Archivo {

    private String nombre;
    private String Url;
    private String tipo;
    public Archivo(String nombre, String Url,String tipo){

        this.nombre = nombre;
        this.Url  = Url;
        this.tipo=tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return Url;
    }

    public String getTipo(){
        return tipo;
    }
}
