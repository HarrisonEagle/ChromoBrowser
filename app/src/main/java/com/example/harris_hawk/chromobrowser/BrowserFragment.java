package com.example.harris_hawk.chromobrowser;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BrowserFragment extends Fragment {
    private Bundle states;
    private int index;
    private String currenturl;
    private String title;

    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return this.title;
    }


    public void setindex(int index){
        this.index=index;
    }

    public int getindex(){
        return index;
    }

    public void setStates(Bundle states){
        this.states=states;
    }

    public Bundle getStates(){
        return this.states;
    }

    public String getCurrentUrl(){
        return this.currenturl;
    }

    public void SetCurrentUrl(String currenturl){
        this.currenturl=currenturl;
    }

    @Override

    public String toString() {
        return currenturl;
    }
}
