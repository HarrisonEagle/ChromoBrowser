package com.example.harris_hawk.chromobrowser;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.AdapterView;

import java.util.List;


public class TabManager extends android.support.v4.app.Fragment{

    public static int num=0;
    public MyAdapter myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabmanager, null);

        final ListView lv=view.findViewById(R.id.tabs);
        myAdapter = new MyAdapter(getContext());

        lv.setAdapter(myAdapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Browser browser=new Browser();
                browser.setindex(position);


            }
        });

        Button addbtn=view.findViewById(R.id.addnewtab);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Browser newbrowser=new Browser();
                View inflatedView = getLayoutInflater().inflate(R.layout.activity_main, null);
                LinearLayout container = inflatedView.findViewById(R.id.container);
                myAdapter.notifyDataSetChanged();
                MainActivity.saveappstate();
                MainActivity.fm.beginTransaction().add(container.getId(),newbrowser).hide(MainActivity.tm).show(MainActivity.tm).commit();
                Log.d("Num of frags",String.valueOf(MainActivity.fm.getFragments().size()));


            }
        });


        return view;
    }






}
