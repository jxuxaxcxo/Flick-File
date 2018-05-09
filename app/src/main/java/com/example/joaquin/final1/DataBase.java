package com.example.joaquin.final1;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Joaquin on 16/12/2017.
 */

public class DataBase {

    DatabaseReference databaseReference;

    public DataBase(){

        databaseReference = FirebaseDatabase.getInstance().getReference("archivos");
    }

    public void enviarDatos(String nombre, String Url,String tipo){

        Archivo archivo = new Archivo(nombre,Url,tipo);

        String clave  =databaseReference.push().getKey();
        databaseReference.child(clave);
        databaseReference.setValue(archivo);

    }

}
