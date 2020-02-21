package com.example.mt19073_contactlist;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactFrag  extends Fragment {
    View view;
    private RecyclerView myRecView;
    private List<MyContact> contactLst;


    public ContactFrag(){

    }

public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
{
    view=inflater.inflate(R.layout.frag_contact,container,false);
    myRecView=(RecyclerView)view.findViewById(R.id.recycler_v);
    RecyclerAdapter recyclerAdapter=new RecyclerAdapter(getContext(),contactLst);
    myRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
    myRecView.setAdapter(recyclerAdapter);
    return view;
}


public  void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);

    contactLst=new ArrayList<>();

    contactLst.add(new MyContact("Swati","9406005503",R.drawable.my_pic));
    contactLst.add(new MyContact("Shalini","9406005503",R.drawable.fav));
    contactLst.add(new MyContact("Richa","9406005503",R.drawable.fav));

    contactLst.add(new MyContact("Priyanka","9406005503",R.drawable.fav));

    contactLst.add(new MyContact("Muskan","9406005503",R.drawable.fav));

    contactLst.add(new MyContact("Pragya","9406005503",R.drawable.fav));

    contactLst.add(new MyContact("Swati","9406005503",R.drawable.my_pic));

}
}
