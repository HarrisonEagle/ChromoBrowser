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
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.AdapterView;

import java.util.List;


public class TabManager extends AppCompatActivity {

    public static int num=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabmanager);

        final ListView lv=findViewById(R.id.tabs);
        final MyAdapter myAdapter = new MyAdapter(this);
        myAdapter.setbfList(MainActivity.browser);
        lv.setAdapter(myAdapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Browser browser=new Browser();
                browser.setindex(position);
                Intent intent=new Intent(TabManager.this,browser.getClass());
                startActivity(intent);
                finish();

            }
        });

        Button addbtn=findViewById(R.id.addnewtab);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Browser newbrowser=new Browser();
                newbrowser.setindex(MainActivity.browser.size());
                BrowserFragment bf=new BrowserFragment();
                bf.setindex(MainActivity.browser.size());
                MainActivity.browser.add(bf);
                Bundle newstate=new Bundle();
                bf.setStates(newstate);
                myAdapter.notifyDataSetChanged();




                //Bundle newstate=new Bundle();
                //MainActivity.states.add(newstate);
                //MainActivity.currentlink[MainActivity.browser.size()]=null;


            }
        });
    }
}
