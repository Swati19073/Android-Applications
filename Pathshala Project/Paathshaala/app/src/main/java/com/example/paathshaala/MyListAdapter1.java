package com.example.paathshaala;

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyListAdapter1 extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> cnamelist;
    private final List<String> inamelist;
    private final List<String> datelist;



    public MyListAdapter1(Activity context, List<String> cnamelist, List<String> inamelist, List<String> datelist) {
        super(context, R.layout.mylist1, cnamelist);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.cnamelist = cnamelist;
        this.inamelist = inamelist;
        this.datelist = datelist;



    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist1, null, true);

        TextView c = (TextView) rowView.findViewById(R.id.Cname_value);

        TextView i = (TextView) rowView.findViewById(R.id.Iname_value);

        TextView d = (TextView) rowView.findViewById(R.id.e_value);
        c.setText(cnamelist.get(position));
        i.setText(inamelist.get(position));
        d.setText(datelist.get(position));

        Log.d("list1msg", "" + cnamelist.get(position));

        return rowView;

    }

    ;
}