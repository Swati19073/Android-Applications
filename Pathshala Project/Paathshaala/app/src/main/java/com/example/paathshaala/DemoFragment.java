package com.example.paathshaala;


import android.content.Intent;
import android.os.Bundle;

//import androidx.fragment.app.Fragment;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment implements View.OnClickListener
{

    private TextView textViewKey;
    private TextView textViewcoursename;
    private TextView textViewinstructor;
    private TextView textViewdate;
    private TextView textViewfees;
    private Button btnMoreInfo,btshowall;

    Course c;

    public DemoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_demo, container, false);
        textViewKey = view.findViewById(R.id.key);
        textViewcoursename = view.findViewById(R.id.coursename);
        textViewinstructor = view.findViewById(R.id.instructor);
        textViewdate = view.findViewById(R.id.date);
        textViewfees = view.findViewById(R.id.fees);
        btnMoreInfo = view.findViewById(R.id.buttonMoreInfo);
        btshowall=view.findViewById(R.id.showall);
        //String message=getArguments().getString("message");

        //ArrayList<Course> list = new ArrayList<Course>();
        //list = (ArrayList<Course>) getArguments().getSerializable("key");
        //textViewKey.setText(list.get().getKey());
        c = (Course) getArguments().get("data");
        textViewKey.setText(c.getKey());

        textViewcoursename.setText(c.getCourseName());
        textViewinstructor.setText(c.getInstructorName());
        textViewdate.setText(c.getStartDate());
        textViewfees.setText(""+c.getFee());
        btshowall.setOnClickListener(this);
        btnMoreInfo.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if(id == R.id.buttonMoreInfo)
        {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            if(c.getKey()=="loading..")
            {

            }
            else {
                intent.putExtra("key", c.getKey());
                startActivity(intent);
            }
        }
        else if(id==R.id.showall)
        {
            Intent intent = new Intent(getContext(), ShowallCourses.class);
            if(c.getKey()=="loading..")
            {

            }
            else {
                startActivity(intent);
            }
        }

    }

}
