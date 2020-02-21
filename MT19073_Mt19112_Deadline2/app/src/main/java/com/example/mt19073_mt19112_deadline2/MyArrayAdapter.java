package com.example.mt19073_mt19112_deadline2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter {

    Context c;
    Activity con;
    List<String> oremail= new ArrayList<>();
    List<String> ortime= new ArrayList<>();
    List<Float> oritemquant= new ArrayList<>();
    List<Float> ortotalCost= new ArrayList<>();
    List<String> oitemName= new ArrayList<>();


    public MyArrayAdapter(Activity context, List<String> oitemName, List<String> oremail, List<String> ortime, List<Float> ortotalCost, List<Float> oritemquant) {
        super(context, R.layout.my_itemview, oitemName);
        // TODO Auto-generated constructor stub

        this.con=context;
        this.oitemName=oitemName;
        this.oremail=oremail;
        this.ortime=ortime;
        this.ortotalCost=ortotalCost;
        this.oritemquant=oritemquant;
    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=con.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_itemview, null,true);

        TextView time1 = (TextView) rowView.findViewById(R.id.time);

        TextView mail1 = (TextView) rowView.findViewById(R.id.emailsp);
        TextView name1 = (TextView) rowView.findViewById(R.id.itemname);

        TextView quant1 = (TextView) rowView.findViewById(R.id.quant);
        TextView cost1 = (TextView) rowView.findViewById(R.id.totalcost);
        time1.setText(ortime.get(position).toString());

        mail1.setText(oremail.get(position));
        name1.setText(oitemName.get(position));
        quant1.setText(oritemquant.get(position)+"");
        cost1.setText(ortotalCost.get(position)+"");

        return rowView;

    };
}
