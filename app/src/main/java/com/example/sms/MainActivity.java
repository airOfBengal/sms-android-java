package com.example.sms;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sms.adapter.SmsAdapter;
import com.example.sms.model.SmsMessage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView smsListView;
    private static final int MULTIPLE_PERMISSIONS = 1;

    String[] permissions= new String[]{
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("SMS Display App");
        //checkForSmsReceivePermissions();

        if (checkPermissions()){
            init();
        }
    }

    private void init(){
        smsListView = findViewById(R.id.smsListView);
        displaySms();
    }

    private void displaySms() {
        List<SmsMessage> smsList = new ArrayList<>();

        Cursor cursor = getContentResolver().query(
                Telephony.Sms.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String sender = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                String message = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY));
                smsList.add(new SmsMessage(sender, message));
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        SmsAdapter adapter = new SmsAdapter(this, R.layout.list_item_sms, smsList);
        smsListView.setAdapter(adapter);
    }

//    void checkForSmsReceivePermissions(){
//        // Check if App already has permissions for receiving SMS
//        if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.RECEIVE_SMS") == PackageManager.PERMISSION_GRANTED) {
//            // App has permissions to listen incoming SMS messages
//        } else {
//
//            // Request permissions from user
//            ActivityCompat.requestPermissions(this, new String[] {"android.permission.RECEIVE_SMS"}, 43391);
//        }
//    }

    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(getBaseContext(),p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == 43391){
//            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                Log.d(this.getClass().getSimpleName(), "SmsAdapter Receive Permissions granted");
//            } else {
//                Log.d(this.getClass().getSimpleName(), "SmsAdapter Receive Permissions denied");
//            }
//        }

        switch (requestCode) {
            case MULTIPLE_PERMISSIONS:{
                if (grantResults.length > 0) {
                    String permissionsDenied = "";
                    for (String per : permissions) {
                        if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                            permissionsDenied += "\n" + per;

                        }

                    }
                    if (permissionsDenied.equals("")){
                        init();
                    }
                }
                return;
            }
        }
    }
}