package com.example.harris_hawk.chromobrowser;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Browser extends android.support.v4.app.Fragment{

    public int index;
    public String currenturl = "https://www.google.co.jp/";


    public void setindex(int index){
        this.index=index;
    }

    private void setSettings(WebSettings setting) {
        //noinspection deprecation
        setting.setJavaScriptEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setAllowFileAccess(true);
        setting.setSupportZoom(true);
        setting.setBuiltInZoomControls(true);
        setting.setDisplayZoomControls(false);
        setting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        setting.setSupportMultipleWindows(false);

        setting.setGeolocationEnabled(true);
        setting.setGeolocationDatabasePath(getContext().getDir("geolocation", 0).getPath());
        setting.setSaveFormData(true);
        setting.setDomStorageEnabled(true);
        setting.setDatabaseEnabled(true);
        setting.setAppCacheEnabled(true);
        setting.setAppCacheMaxSize(Long.MAX_VALUE);
        setting.setAppCachePath(getContext().getDir("cache", 0).getPath());
        setting.setLoadWithOverviewMode(true);
        setting.setUseWideViewPort(true);
        setting.setTextZoom(Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("text_size", "100")));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.browser, null);
        index = MainActivity.fm.getFragments().size() - 2;

        final WebView webview=view.findViewById(R.id.webview);

        WebSettings setting = webview.getSettings();
        setSettings(setting);
        webview.getSettings().setJavaScriptEnabled(true);

        final Button gotab=view.findViewById(R.id.gotab);
        final EditText urltext=view.findViewById(R.id.url);

        final Button go=view.findViewById(R.id.go);

        final Button back=view.findViewById(R.id.back);
        final Button forward=view.findViewById(R.id.forward);
        final Button refresh=view.findViewById(R.id.refresh);

        MainActivity.tm.myAdapter.notifyDataSetChanged();

        Log.d("index:",String.valueOf(index));

        urltext.setText(currenturl);
        webview.loadUrl(currenturl);

        webview.getSettings().setSupportMultipleWindows(true);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                if (!URLUtil.isValidUrl(url))
                    return true;
                Log.d("appo", "shouldOverride:" + url);
                urltext.setText(url); // リンクをクリックした時にTextViewにurlを表示する。
                currenturl = url;
                MainActivity.tm.myAdapter.notifyDataSetChanged();
                MainActivity.saveappstate();
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //过滤
            }



            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });


        webview.setWebChromeClient(new WebChromeClient() {


            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog,
                                          boolean isUserGesture, Message resultMsg) {


                WebView newWebView = new WebView(getContext());
                newWebView.getSettings().setJavaScriptEnabled(true);
                newWebView.getSettings().setSupportZoom(true);
                newWebView.getSettings().setBuiltInZoomControls(true);
                newWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
                newWebView.getSettings().setSupportMultipleWindows(true);
                view.addView(newWebView);
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(newWebView);
                resultMsg.sendToTarget();

                newWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        urltext.setText(url); // リンクをクリックした時にTextViewにurlを表示する。
                        currenturl = url;
                        MainActivity.tm.myAdapter.notifyDataSetChanged();
                        MainActivity.saveappstate();
                        return true;
                    }
                });

                return true;
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
                currenturl = urltext.getText().toString();
                MainActivity.tm.myAdapter.notifyDataSetChanged();
                MainActivity.saveappstate();

            }
        });

        gotab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fm.beginTransaction().hide(Browser.this).show(MainActivity.tm).commit();
            }
        });




        return view;
    }







}
