package com.example.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkForSmsReceivePermissions();

    }

    void checkForSmsReceivePermissions(){
        // Check if App already has permissions for receiving SMS
        if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.RECEIVE_SMS") == PackageManager.PERMISSION_GRANTED) {
            // App has permissions to listen incoming SMS messages
        } else {

            // Request permissions from user
            ActivityCompat.requestPermissions(this, new String[] {"android.permission.RECEIVE_SMS"}, 43391);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 43391){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d(this.getClass().getSimpleName(), "Sms Receive Permissions granted");
            } else {
                Log.d(this.getClass().getSimpleName(), "Sms Receive Permissions denied");
            }
        }
    }
}