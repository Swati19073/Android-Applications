package com.example.paathshaala;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyfeedbacklistAdapter  extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> name1 ;
    private final List<String> msg1;



    public MyfeedbacklistAdapter(Activity context, List<String> name1,List<String> msg1) {
        super(context, R.layout.myfeedbacklist,name1);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.name1=name1;
        this.msg1=msg1;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.myfeedbacklist, null,true);

        TextView c = (TextView) rowView.findViewById(R.id.name);

        TextView i = (TextView) rowView.findViewById(R.id.msg);


        c.setText(name1.get(position));
        i.setText(msg1.get(position));



        return rowView;

    };
}