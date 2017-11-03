package com.example.lcogorno.prova1;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_PHONE_NUMBERS;

public class MainActivity extends AppCompatActivity {
    boolean enabled;
    Button enableButton;
    TextView action_log;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText txtUrl = new EditText(this);


        action_log = findViewById(R.id.action_log);


        txtUrl.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("IP Insertion");
        builder.setMessage("Insert Last Ip Address Number");
        builder.setView(txtUrl);
        builder.setPositiveButton("CONFIRM", new CustomOnClickListener(action_log, getApplicationContext(), txtUrl));

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        builder.show();






        //   Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
    //   i.addCategory(Intent.CATEGORY_DEFAULT);
    //   i.setData(Uri.parse("package:" + "com.google.android.youtube"));
    //   startActivity(i);



        /*
        enableButton = findViewById(R.id.enableButton);

        try {
          enabled = getPackageManager().getApplicationInfo("com.google.android.youtube", 0).enabled;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.d("ERROR", "Invalid package");
        }

        if(enabled)
        {
            enableButton.setText("Disable");
            getPackageManager().setApplicationEnabledSetting("com.google.android.youtube", PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 0);
            recreate();
        }
        else
        {
            enableButton.setText("Enable");
            getPackageManager().setApplicationEnabledSetting("com.google.android.youtube", PackageManager.COMPONENT_ENABLED_STATE_ENABLED, 0);
            recreate();
        }
        */
    }

}
