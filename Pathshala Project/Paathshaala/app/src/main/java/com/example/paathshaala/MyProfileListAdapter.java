package com.example.paathshaala;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

class MyProfileListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] names;
    private final String[] mail;
    private final Integer[] pics;
    public MyProfileListAdapter(Activity context, String[] names, String[] mail, Integer[] pics) {
        super(context, R.layout.pro_list, names);
        this.context=context;
        this.names=names;
        this.mail=mail;
        this.pics=pics;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.pro_list, null,true);

        TextView nametv = (TextView) rowView.findViewById(R.id.name);
        ImageView image = (ImageView) rowView.findViewById(R.id.pic);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.mail);

        nametv.setText(names[position]);
        image.setImageResource(pics[position]);
        subtitleText.setText(mail[position]);

        return rowView;

    };
}
