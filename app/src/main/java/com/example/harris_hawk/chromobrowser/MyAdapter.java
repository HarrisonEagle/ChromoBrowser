package com.example.harris_hawk.chromobrowser;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    List<BrowserFragment> bflist;

    public MyAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setbfList(List<BrowserFragment> bflist) {
        this.bflist = bflist;
    }

    @Override
    public int getCount() {
        return bflist.size();
    }

    @Override
    public Object getItem(int position) {
        return bflist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return bflist.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.adapter,parent,false);
        String url;
        if(bflist.get(position).getCurrentUrl()==null){
            url="New Tab";

        }else{
            url=bflist.get(position).getCurrentUrl();

        }

        ((Button)convertView.findViewById(R.id.taburl)).setText(url);
        ((Button)convertView.findViewById(R.id.taburl)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Browser browser=new Browser();
                browser.setindex(position);
                Intent intent=new Intent(context,browser.getClass());
                context.startActivity(intent);

            }
        });

        ((Button)convertView.findViewById(R.id.deltab)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.browser.remove(position);
                notifyDataSetChanged();

            }
        });

        return convertView;
    }
}
