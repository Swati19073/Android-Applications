package com.example.mt19073_contactlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {


    Context mctext;
    List<MyContact> data;

    public RecyclerAdapter(Context mctext, List<MyContact> data) {
        this.mctext = mctext;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(mctext).inflate(R.layout.contact_layout,parent,false);
        Log.d("msg","successfuly inflated activity");
        MyViewHolder mholder=new MyViewHolder(view);
                return mholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.phnTv.setText(data.get(position).getName());

        holder.phnNumTv.setText(data.get(position).getPhnNum());
        holder.img.setImageResource(data.get(position).getPic());
        Log.d("msg"," bind view recycler");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView phnTv;
        private TextView phnNumTv;
        private ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("msg"," my view holder recycler activity");

            phnTv=(TextView)itemView.findViewById(R.id.user_name);
            phnNumTv=(TextView)itemView.findViewById(R.id.phone_num);
            img=(ImageView)itemView.findViewById(R.id.img_of_user);

        }


    }

}
