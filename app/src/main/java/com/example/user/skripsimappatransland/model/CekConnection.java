package com.example.user.skripsimappatransland.model;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by User on 9/6/2017.
 */

public class CekConnection {

    Context context;

    public CekConnection(Context context){
        this.context = context;
    }

    public Boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if(cm != null){
            NetworkInfo info = cm.getActiveNetworkInfo();
            if(info != null){
                if(info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
}
