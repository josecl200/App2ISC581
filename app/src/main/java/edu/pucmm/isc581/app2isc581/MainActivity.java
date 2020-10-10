package edu.pucmm.isc581.app2isc581;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static int EL_PEPE = 200;

    Button aceptar, cancelar;
    Switch switchCam, switchLocation, switchStorage, switchPhone, switchContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aceptar = findViewById(R.id.aceptarText);
        cancelar = findViewById(R.id.limpiarText);
        switchCam = findViewById(R.id.camSwitch);
        switchLocation = findViewById(R.id.locationSwitch);
        switchStorage = findViewById(R.id.storageSwitch);
        switchPhone = findViewById(R.id.phoneSwitch);
        switchContacts = findViewById(R.id.contactsSwitch);

        switchCam.setChecked(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
        switchLocation.setChecked(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        switchStorage.setChecked(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        switchPhone.setChecked(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED);
        switchContacts.setChecked(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED);

        aceptar.setOnClickListener(v -> {
            boolean permissionsSet = false;
            ArrayList<String> permisos = new ArrayList<>();
            if (switchCam.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    permisos.add(Manifest.permission.CAMERA);
                }
            }
            if (switchContacts.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    permisos.add(Manifest.permission.READ_CONTACTS);
                }
            }
            if (switchPhone.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    permisos.add(Manifest.permission.CALL_PHONE);

                }
            }
            if (switchStorage.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    permisos.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                }
            }
            if (switchLocation.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    permisos.add(Manifest.permission.ACCESS_FINE_LOCATION);

                }
            }

            Intent intent = new Intent(v.getContext(),IntentActivity.class);
            Log.wtf("PERMISOS",permisos.toString());
            permissionsSet = ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

            if (permisos.size()>0)
                ActivityCompat.requestPermissions(MainActivity.this,permisos.toArray(new String[0]), EL_PEPE);
            else if (permissionsSet)
                startActivity(intent);



        });

        cancelar.setOnClickListener(v -> {
            finish();
        });
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == EL_PEPE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(MainActivity.this, "Permisos Concedido", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),IntentActivity.class);
                startActivity(intent);
            }
        }
    }
}