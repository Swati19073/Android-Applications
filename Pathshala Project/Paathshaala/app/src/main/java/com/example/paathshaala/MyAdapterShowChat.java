package com.example.paathshaala;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterShowChat  extends ArrayAdapter {
    private final Activity context;
    List<String> username;
    List<String> inamelist;
   // List<String> msg= new ArrayList<>();

    public MyAdapterShowChat(Activity context, List<String> username,List<String> inamelist) {
        super(context, R.layout.showchat, username);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.username=username;
        this.inamelist=inamelist;
        //Log.d("In_list",username.get(0));
    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.showchat, null,true);

        TextView username1 = (TextView) rowView.findViewById(R.id.Cname_value);

        //Log.d("list","list "+username.get(position));

        username1.setText(username.get(position));


        return rowView;

    };
}

