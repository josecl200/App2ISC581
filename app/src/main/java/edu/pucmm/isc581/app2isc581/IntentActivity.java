package edu.pucmm.isc581.app2isc581;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;

public class IntentActivity extends AppCompatActivity {

    Button storage,location,camera,phone,contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        storage = findViewById(R.id.storageBtn);
        location = findViewById(R.id.locationBtn);
        camera = findViewById(R.id.cameraBtn);
        phone = findViewById(R.id.phoneBtn);
        contacts = findViewById(R.id.contactsBtn);

        storage.setOnClickListener((v) -> {
            if (ContextCompat.checkSelfPermission(IntentActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)==0){
                Intent storageIntent = new Intent(Intent.ACTION_VIEW);
                storageIntent.setType("image/*");
                storageIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(storageIntent);
            } else
                Toast.makeText(IntentActivity.this, "No tiene los permisos necesarios para esta acción.", Toast.LENGTH_LONG).show();

        });

        location.setOnClickListener((v) -> {
            if (ContextCompat.checkSelfPermission(IntentActivity.this, Manifest.permission.READ_CONTACTS)==0) {
                Intent locationIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(locationIntent);
            } else
                Toast.makeText(IntentActivity.this, "No tiene los permisos necesarios para esta acción.", Toast.LENGTH_LONG).show();

        });

        camera.setOnClickListener((v) -> {
            if (ContextCompat.checkSelfPermission(IntentActivity.this, Manifest.permission.CAMERA)==0) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
            } else
                Toast.makeText(IntentActivity.this, "No tiene los permisos necesarios para esta acción.", Toast.LENGTH_LONG).show();

        });

        phone.setOnClickListener((v) -> {
            if (ContextCompat.checkSelfPermission(IntentActivity.this, Manifest.permission.CALL_PHONE)==0) {
                Intent phoneIntent = new Intent(Intent.ACTION_CALL_BUTTON);
                startActivity(phoneIntent);
            } else
                Toast.makeText(IntentActivity.this, "No tiene los permisos necesarios para esta acción.", Toast.LENGTH_LONG).show();

        });

        contacts.setOnClickListener((v) -> {
            if (ContextCompat.checkSelfPermission(IntentActivity.this, Manifest.permission.READ_CONTACTS)==0) {
                Intent contactsIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivity(contactsIntent);
            } else
                Toast.makeText(IntentActivity.this, "No tiene los permisos necesarios para esta acción.", Toast.LENGTH_LONG).show();

        });




    }

}