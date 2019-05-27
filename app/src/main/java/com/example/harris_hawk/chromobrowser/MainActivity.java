package com.example.harris_hawk.chromobrowser;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected static List<BrowserFragment> browser=new ArrayList<BrowserFragment>();
    protected Handler mHandler=new Handler();









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Browser firstbrowser=new Browser();
                firstbrowser.setindex(0);
                BrowserFragment bf=new BrowserFragment();
                bf.setindex(0);
                browser.add(bf);
                Bundle newstate=new Bundle();
                bf.setStates(newstate);
                Intent intent=new Intent(MainActivity.this,firstbrowser.getClass());
                startActivity(intent);
                finish();

            }
        },2000);



















    }
}
