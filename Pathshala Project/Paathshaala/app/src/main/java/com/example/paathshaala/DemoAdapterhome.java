package com.example.paathshaala;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//import androidx.fragment.v4.app.Fragment;
//import androidx.fragment.v4.app.FragmentManager;
//import androidx.fragment.v4.app.FragmentStatePagerAdapter;
public class DemoAdapterhome extends FragmentStatePagerAdapter {
    ArrayList<Course> list = new ArrayList<Course>();
    int flag=1;
    public DemoAdapterhome(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        DemoFragment demoFragment=new DemoFragment();
        Bundle bundle=new Bundle();
        // position=position+1;






        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference().child("NewsFeed");

        try {
            Query q=ref1.orderByKey();
            // For counting Users
            q.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int count=0;

                    for (DataSnapshot sn : dataSnapshot.getChildren()) {
                        PostCourseinfo  p=sn.getValue(PostCourseinfo.class);
                            /*if(count==4)
                            {break;}
                            else {*/
                                Course c1 = new Course(""+sn.getKey() , "" + p.getCourse1(), "" + p.getName1(), "" + p.getDate1(), (p.getFees1()));
                                list.add(c1);
                            //}

                            count++;

                        setFlag();
                    }


                    // e1.setText(keylist.get(1));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


                public void onCancelled(FirebaseError firebaseError) {

                }
            });












        }catch(Exception ex){}



        Course c1 = new Course("01","daa","shyam","23/7/2019","2000");
        Course c2 = new Course("02","daa","shyam","23/7/2019","2000");
        Course c3 = new Course("03","grad","ravi","23/7/2019","2000");
        Course c4 = new Course("04","mc","raj","23/7/2019","2000");
        //list.add(c1);
        /*list.add(c2);
        list.add(c3);
        list.add(c4);*/


        if(flag==0) {
            bundle.putSerializable("data", list.get(position));
            position = position + 1;
            //bundle.putString("message","Key");
            demoFragment.setArguments(bundle);
            return demoFragment;
        }
        else
        {
            Course c = new Course("loading..","loading..","loading..","loading..","loading..");

            ArrayList<Course> list1 = new ArrayList<Course>();
            list1.add(c);
            list1.add(c);
            list1.add(c);
            list1.add(c);
            bundle.putSerializable("data", list1.get(position));
            position = position + 1;
            //bundle.putString("message","Key");
            demoFragment.setArguments(bundle);
            return demoFragment;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    public void setFlag()
    {
        flag=0;
    }
    public int passObj(PostCourseinfo p,int count,String k)
    {

        if(count%4==0)
        {
            Course c1 = new Course(""+k,""+p.getCourse1(),""+p.getName1(),""+p.getDate1(),(p.getFees1()));
            list.add(c1);
        }
        else if(count%4==1)
        {
            Course c2 = new Course(""+k,""+p.getCourse1(),""+p.getName1(),""+p.getDate1(),(p.getFees1()));

            list.add(c2);
        }
        else if(count%4==2)
        {
            Course c3 = new Course(""+k,""+p.getCourse1(),""+p.getName1(),""+p.getDate1(),(p.getFees1()));
            list.add(c3);
        }
        else if(count%4==3)
        {

            Course c4 = new Course(""+k,""+p.getCourse1(),""+p.getName1(),""+p.getDate1(),(p.getFees1()));
            list.add(c4);
        }

        count++;


















        return count;


    }

}

