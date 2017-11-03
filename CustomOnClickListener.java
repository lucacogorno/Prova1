package com.example.lcogorno.prova1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lcogorno on 03/11/2017.
 */

public class CustomOnClickListener implements DialogInterface.OnClickListener {

    String networkSSID;
    String networkPass;
    String networkIp;
    Context context;
    TextView action_log;
    EditText txtUrl;

    public CustomOnClickListener(TextView action_log, Context context, EditText txtUrl)
    {
        this.txtUrl = txtUrl;
        this.action_log = action_log;
        this.context = context;
        networkSSID = "apassadhoc";
        networkPass = "apassadhoc";
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {


        String received = txtUrl.getText().toString();
        Log.d("RECEIVED", received);
        int inserted = Integer.parseInt(received);
        Log.d("INSERTED", Integer.toString(inserted));
        networkIp = "192.168.173." + Integer.toString(100 + inserted);
        Log.d("IP", networkIp);

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";
        conf.preSharedKey = "\"" + networkPass + "\"";
        conf.status = WifiConfiguration.Status.ENABLED;
        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        conf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        conf.allowedProtocols.set(WifiConfiguration.Protocol.RSN);


        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        try {
            WifiConnection.setIpAssignment("STATIC", conf);
            WifiConnection.setIpAddress(InetAddress.getByName(networkIp), 24, conf);
            WifiConnection.setGateway(InetAddress.getByName("192.168.173.1"), conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        wifiManager.addNetwork(conf);

        List<WifiConfiguration> wifiConfigurations = wifiManager.getConfiguredNetworks();
        for(WifiConfiguration wificonf : wifiConfigurations)
        {
            if(wificonf.SSID.equals("\"" + networkSSID + "\""))
            {
                action_log.append(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()) + ": Network added with IP: " + networkIp + "\n");
                break;
            }
        }

        Uri packageURI = Uri.parse("package:"+"com.honeywell.demos.beamdemo");
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        uninstallIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(uninstallIntent);
        action_log.append(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()) + "NFC Beam Deleted\n");

        packageURI = Uri.parse("package:"+"com.honeywell.demos.nfctagdemo");
        uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        uninstallIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(uninstallIntent);
        action_log.append(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()) + "NFC Tag Demo Deleted\n");

        Intent svc = new Intent(context, OverlayShowingService.class);
        svc.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(svc);
    }
}
