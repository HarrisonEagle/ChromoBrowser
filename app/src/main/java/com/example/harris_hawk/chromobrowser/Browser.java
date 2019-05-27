package com.example.harris_hawk.chromobrowser;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Browser extends AppCompatActivity{

    public static String currenturl;
    public static int index;


    public void setindex(int index){
        this.index=index;
    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        final WebView webview=findViewById(R.id.webview);
        final BrowserFragment bf=MainActivity.browser.get(index);



        webview.getSettings().setJavaScriptEnabled(true);

        final Button gotab=findViewById(R.id.gotab);
        final EditText urltext=findViewById(R.id.url);

        final Button go=findViewById(R.id.go);

        final Button back=findViewById(R.id.back);
        final Button forward=findViewById(R.id.forward);
        final Button refresh=findViewById(R.id.refresh);




        Log.d("index:",String.valueOf(index));

        if(bf.getCurrentUrl()==null){
            urltext.setText("https://www.google.co.jp/");
            webview.loadUrl("https://www.google.co.jp/");


        }else{
            webview.restoreState(MainActivity.browser.get(index).getStates());
            //webview.loadUrl(currenturl);
            urltext.setText(webview.getUrl());

        }

        webview.getSettings().setSupportMultipleWindows(true);

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView View, String url) {
                urltext.setText(url); // リンクをクリックした時にTextViewにurlを表示する。
                bf.SetCurrentUrl(urltext.getText().toString());
                bf.setTitle(webview.getTitle());
                return false;
            }

        });


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.reload();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.goBack();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview.goForward();
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urltemp=urltext.getText().toString();
                if(urltemp.indexOf("https://")==0){
                    webview.loadUrl(urltemp);
                }else if(!urltemp.contains("https://")){
                    urltext.setText("https://"+urltemp);
                    webview.loadUrl("https://"+urltemp);
                }
                currenturl=urltext.getText().toString();
            }
        });

        gotab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle outstate=new Bundle();
                webview.saveState(outstate);
                Browser.super.onSaveInstanceState(outstate);


                bf.setStates(outstate);

                bf.setindex(index);

                MainActivity.browser.set(index,bf);
                Intent intent=new Intent(Browser.this,TabManager.class);
                startActivity(intent);


                Log.d("tag",String.valueOf(MainActivity.browser.indexOf(Browser.this)));
                finish();

            }
        });
    }







}
