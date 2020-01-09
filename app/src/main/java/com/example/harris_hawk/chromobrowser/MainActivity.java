package com.example.harris_hawk.chromobrowser;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fm;
    public static TabManager tm = new TabManager();
    public static int containerid = R.id.container;
    public static Context context;

    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public static void delDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().remove(key).apply();
    }


    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("triggerexit","Exit!");
        try{

        }catch (Exception e){

        }
    }


    public static void saveappstate(){
        JSONArray jsonArray = new JSONArray();
        try {
            for (int i = 1; i < fm.getFragments().size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("currenturl", ((Browser) fm.getFragments().get(i)).currenturl);
                jsonArray.put(jsonObject);
            }
            setDefaults("appstate",jsonArray.toString(),context);
        }catch (Exception e){
            Log.d("Error!",e.toString());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        fm=getSupportFragmentManager();
        fm.beginTransaction().add(containerid,tm).hide(tm).commit();
        if(getDefaults("appstate",context)==null){
            Browser browser = new Browser();
            fm.beginTransaction().add(containerid,browser).show(browser).commit();
        }else{
            try {
                JSONArray jsonArray = new JSONArray(getDefaults("appstate", context));
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Browser browser = new Browser();
                    browser.currenturl = jsonObject.getString("currenturl");
                    fm.beginTransaction().add(containerid,browser).show(browser).commit();
                }
                if(jsonArray.length()==0){
                    Browser browser = new Browser();
                    fm.beginTransaction().add(containerid,browser).show(browser).commit();
                }
            }catch (Exception e){
                Log.d("Error!",e.toString());
                Browser browser = new Browser();
                fm.beginTransaction().add(containerid,browser).show(browser).commit();
            }


        }


    }
}
