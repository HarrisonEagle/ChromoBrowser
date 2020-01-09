package com.example.harris_hawk.chromobrowser;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;

    public MyAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return MainActivity.fm.getFragments().size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.fm.getFragments().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.adapter,parent,false);
        String url = "";
        if(position==0){
            LinearLayout linearLayout = convertView.findViewById(R.id.child);
            linearLayout.setVisibility(View.GONE);
        }else{
           url = ((Browser)MainActivity.fm.getFragments().get(position)).currenturl;
        }
        ((Button)convertView.findViewById(R.id.taburl)).setText(String.valueOf(url));
        ((Button)convertView.findViewById(R.id.taburl)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Num of frags",String.valueOf(MainActivity.fm.getFragments().size()));
                MainActivity.fm.beginTransaction().hide(MainActivity.tm).show(MainActivity.fm.getFragments().get(position)).commit();

            }
        });

        ((Button)convertView.findViewById(R.id.deltab)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fm.beginTransaction().remove(MainActivity.fm.getFragments().get(position)).commit();
                notifyDataSetChanged();
                MainActivity.saveappstate();
            }
        });

        return convertView;
    }
}
