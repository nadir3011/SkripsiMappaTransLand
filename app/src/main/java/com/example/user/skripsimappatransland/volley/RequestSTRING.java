package com.example.user.skripsimappatransland.volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.user.skripsimappatransland.activity.MainActivity;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;


public class RequestSTRING{

    private Context context;
    private ProgressDialog progressDialog;
    private String urlnya,title,message,tagString;
    private String [] keynya;
    private String [] valuenya;
    private int jumlah = 0;
    private int data = 0;
    public RequestSTRING(){

    }

    public RequestSTRING(Context context){
        this.context = context;
    }

    public void setTagString(String tagString) {
        this.tagString = tagString;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUrlnya(String urlnya) {
        this.urlnya = urlnya;
    }

    public void setKeynya(String[] keynya) {
        this.keynya = keynya;
    }

    public void setValuenya(String[] valuenya) {
        this.valuenya = valuenya;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public interface VolleyCallBack{
        void onSuccess(String result) throws JSONException;
    }

    public void string_status(final VolleyCallBack callback){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlnya, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    callback.onSuccess(response);
                }catch (Exception e){
                    errornya(context,"POST : "+e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errornya(context,"Volley : "+error.getMessage());
            }

        }){
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params =new HashMap<String, String>();
                for(int a = 0; a < keynya.length; a++){
                    params.put(keynya[a],valuenya[a]);
                }
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, "goodry");
    }

    public void string_post(final VolleyCallBack callBack){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setTitle(title);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(7000);
                }catch (Exception e){
                    progressDialog.dismiss();
                    errornya(context,e.getMessage());
                }
                progressDialog.dismiss();
//                AppController.getInstance().cancelPendingRequests(tagString);
            }
        }).start();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlnya, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    callBack.onSuccess(response);
                }catch (Exception e){
                    progressDialog.dismiss();
                    errornya(context,"POST : "+e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                errornya(context,"Volley : "+error.getMessage());
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params =new HashMap<String, String>();
                for(int a = 0; a < keynya.length; a++){
                    params.put(keynya[a],valuenya[a]);
                }
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, tagString);
    }

    public void string_get(final VolleyCallBack callBack){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlnya, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    callBack.onSuccess(response);
                }catch (Exception e){
                    progressDialog.dismiss();
                    errornya(context,"POST : "+e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(tagString, "Error GET : "+error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest, tagString);
    }

    public void string_request(final VolleyCallBack callBack){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setTitle(title);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(7000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();

        StringRequest stringRequest = new StringRequest(urlnya, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    callBack.onSuccess(response);
                }catch (Exception e){
                    progressDialog.dismiss();
                    errornya(context,"POST : "+e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(tagString, "Error POST : "+error.getMessage());
                progressDialog.dismiss();
                errornya(context,"Volley : "+error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(stringRequest, tagString);
    }

    public void string_getHeaders(final VolleyCallBack callBack){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setTitle(title);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(7000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlnya, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    callBack.onSuccess(response);
                }catch (Exception e){
                    progressDialog.dismiss();
                    errornya(context,"POST : "+e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(tagString, "Error GET HEADERS : "+error.getMessage());
                progressDialog.dismiss();
                errornya(context,"VOLLEY ("+urlnya+")"+error.getMessage());
            }
        }){
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                for (int i = 0; i < keynya.length; i++){
                    headers.put(keynya[i],valuenya[i]);
                }
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest, tagString);
    }

    public static void errornya(Context context, String s){
//        Intent i = new Intent(context,MainActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(i);
        Toast.makeText(context,"Error "+s,Toast.LENGTH_LONG).show();
    }
}
