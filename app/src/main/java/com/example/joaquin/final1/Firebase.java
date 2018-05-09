package com.example.joaquin.final1;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;

/**
 * Created by Joaquin on 16/12/2017.
 */

public class Firebase  {

    private static final int PICK_IMAGE_REQUEST=234;
    private ImageView imageView;
    private Uri filepath;
    private StorageReference storageReference;
    private String Url;
    Activity activity;
    private TextView txt;
    DataBase dataBase;
    String nombre,tipo;

    public Firebase(Activity activity){
        this.activity = activity;
        storageReference = FirebaseStorage.getInstance().getReference();
        txt = activity.findViewById(R.id.estado);
        dataBase = new DataBase();
    }



    private void uploadFile(){


        StorageReference riversRef = storageReference.child("images/" + nombre);

        riversRef.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Url = downloadUrl.toString();

                        dataBase.enviarDatos(nombre, Url,tipo);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                    }
                });
    }

    public void upload(String path,String nombre,String tipo){

        this.nombre=nombre;
        filepath = Uri.parse(path);
        this.tipo = tipo;
        uploadFile();

    }

    public String getUrl(){
        return Url;
    }


}
