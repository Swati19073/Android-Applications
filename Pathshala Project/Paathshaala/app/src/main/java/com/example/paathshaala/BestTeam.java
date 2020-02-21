package com.example.paathshaala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class BestTeam extends AppCompatActivity {

    ListView list1;
    String[] names ={
            "Akshaj Anil Patil","Ananya Tyagi",
            "Ashima","Chaudhari Ganesh Vilas",
            "Dhawal Singh Pundir","Nitindeep Singh","Ravneet Kaur Gill","Shalini Bhardwaj","Shraddha Gupta","Swati Verma"
    };

    String[] mail ={
            "akshaj19111@iiitd.ac.in","ananya19114@iiitd.ac.in",
            "ashima19031@iiitd.ac.in","chaudhari19116@iiitd.ac.in",
            "dhawal19120@iiitd.ac.in","nitindeep19069@iiitd.ac.in","ravneet19080@iiitd.ac.in","shalini19045@iiitd.ac.in"," shraddha19072@iiitd.ac.in","swati19073@iiitd.ac.in"
    };

    Integer[] pics={R.drawable.akshaj,R.drawable.ananya, R.drawable.ashima,R.drawable.ganeshnew, R.drawable.dhawal,
            R.drawable.nitin,R.drawable.ravneet,R.drawable.shalini,R.drawable.shraddha,R.drawable.swati
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_team);
        MyProfileListAdapter adapter=new MyProfileListAdapter(this, names, mail,pics);
        list1=(ListView)findViewById(R.id.listview);
        list1.setAdapter(adapter);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if(position == 0) {
                    //Toast.makeText(getApplicationContext(),"Place Your First Option Code",Toast.LENGTH_SHORT).show(); }
                }
                else if(position == 1) {
                    //Toast.makeText(getApplicationContext(),"Place Your Second Option Code",Toast.LENGTH_SHORT).show();
                }

                else if(position == 2) {

                   // Toast.makeText(getApplicationContext(),"Place Your Third Option Code",Toast.LENGTH_SHORT).show();
                    }
                else if(position == 3) {

                    //Toast.makeText(getApplicationContext(),"Place Your Forth Option Code",Toast.LENGTH_SHORT).show();
                }
                else if(position == 4) {

                   // Toast.makeText(getApplicationContext(),"Place Your Fifth Option Code",Toast.LENGTH_SHORT).show();
                }
                else if(position == 5) {

                    Toast.makeText(getApplicationContext(),"Place Your Sixth Option Code",Toast.LENGTH_SHORT).show();
                }
                else if(position == 6) {

                    //Toast.makeText(getApplicationContext(),"Place Your Seventh Option Code",Toast.LENGTH_SHORT).show();
                }
                else if(position == 7) {

                    //Toast.makeText(getApplicationContext(),"Place Your Eighth Option Code",Toast.LENGTH_SHORT).show();
                }
                else if(position == 8) {

                   // Toast.makeText(getApplicationContext(),"Place Your Ninth Option Code",Toast.LENGTH_SHORT).show();
                }
                else if(position == 9) {

                    //Toast.makeText(getApplicationContext(),"Place Your Tenth Option Code",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
