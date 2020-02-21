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

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> cnamelist ;
    private final List<String> inamelist;
    private final List<String> datelist;
    private final List<String> feelist;


    public MyListAdapter(Activity context, List<String> cnamelist,List<String> inamelist, List<String> datelist,List<String> feelist) {
        super(context, R.layout.mylist,cnamelist);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.cnamelist=cnamelist;
        this.inamelist=inamelist;
        this.datelist=datelist;
        this.feelist=feelist;


    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView c = (TextView) rowView.findViewById(R.id.Cname_value);

        TextView i = (TextView) rowView.findViewById(R.id.Iname_value);

        TextView f = (TextView) rowView.findViewById(R.id.fees_value);

        TextView d = (TextView) rowView.findViewById(R.id.date_value);
        c.setText(cnamelist.get(position));
        i.setText(inamelist.get(position));
        f.setText(feelist.get(position));
        d.setText(datelist.get(position));

        Log.d("listmsg",""+cnamelist.get(position));

        return rowView;

    };
}