package com.example.mt19073_contactlist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.zip.Inflater;

public class FavFrag extends Fragment {
    View view;
    public FavFrag(){

    }

    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate)
    {
        view=inflater.inflate(R.layout.frag_fav,container,false);
        Log.d("msg","fav frag activity");
        return view;
    }
}
