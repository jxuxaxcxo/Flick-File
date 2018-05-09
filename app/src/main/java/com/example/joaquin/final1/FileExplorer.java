package com.example.joaquin.final1;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileExplorer extends AppCompatActivity {

    public static final int PERMISSIONS_REQUEST_CODE = 0;
    public static final int FILE_PICKER_REQUEST_CODE = 1;

    private String filePath;
    private ImageView imageView;
    private TextView text;
    Firebase  conexion;
    private String name;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_explorer);

        imageView = findViewById(R.id.imageView2);
        text = findViewById(R.id.estado);

        ImageButton pickButton = (ImageButton) findViewById(R.id.imageButton);
        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionsAndOpenFilePicker();
            }
        });


    }

    public void uploadImage(){

        conexion = new Firebase(FileExplorer.this);
        conexion.upload(filePath,name,type);
        imageView.setVisibility(View.INVISIBLE);
    }

    private void checkPermissionsAndOpenFilePicker() {
        String permission = android.Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            openFilePicker();
        }
    }

    private void showError() {
        Toast.makeText(this, "Allow external storage reading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker();
                } else {
                    showError();
                }
            }
        }
    }

    private void openFilePicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .withHiddenFiles(false)
                .withTitle("PICK YOUR FILE")
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);

            if (path != null) {
                Log.d("Path: ", path);
                Toast.makeText(this, "Picked file: " + path, Toast.LENGTH_LONG).show();

                int index = path.lastIndexOf("/");
                name = path.substring(index);
                int indexType = name.lastIndexOf(".");
                type = name.substring(indexType);

                File file = new File(path);
                Uri u = Uri.fromFile(file);
                filePath = u.toString();

                imageView.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.drawable.chimuelo2);
                TouchListener touchListener = new TouchListener(this,this);

            }
        }
    }



}
