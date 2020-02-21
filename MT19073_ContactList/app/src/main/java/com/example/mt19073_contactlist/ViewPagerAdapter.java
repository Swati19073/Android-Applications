package com.example.mt19073_contactlist;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragList= new ArrayList<>();
    private final List<String> names=new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {


        super(fm);
        Log.d("msg","view pager activity");

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }

    public void InsertFrag(Fragment frag, String name)
    {
        Log.d("msg","view pager activity");
        fragList.add(frag);
        names.add(name);
    }
}
