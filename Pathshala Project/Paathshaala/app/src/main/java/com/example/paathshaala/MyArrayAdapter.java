package com.example.paathshaala;
import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter {
    private final Activity context;
    List<String> username= new ArrayList<>();
    List<String> time= new ArrayList<>();
    List<String> msg= new ArrayList<>();

    public MyArrayAdapter(Activity context, List<String> username,List<String> msg, List<String> time) {
        super(context, R.layout.my_message, username);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.username=username;
        this.msg=msg;
        this.time=time;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_message, null,true);

        TextView username1 = (TextView) rowView.findViewById(R.id.time);

        TextView msgtxt = (TextView) rowView.findViewById(R.id.username);
        TextView time1 = (TextView) rowView.findViewById(R.id.msg_text);

        username1.setText(username.get(position));

        msgtxt.setText(msg.get(position));
        time1.setText(time.get(position));

        return rowView;

    };
}
