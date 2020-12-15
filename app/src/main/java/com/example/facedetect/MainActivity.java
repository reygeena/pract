package com.example.facedetect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity  {


    CardView attendance;
    CardView marksheet;
    CardView events;
    CardView logout;


    public static final String TAG = "MyTag";
    private TextView mOutputText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_layout);
        attendance=findViewById(R.id.attendance);
        marksheet=findViewById(R.id.marksheet);
        events=findViewById(R.id.events);
        logout=findViewById(R.id.logout);

        if(!StoreUserDetail.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,login.class));
        }

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>()
        {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task)
            {
                if (task.isSuccessful())
                {
                    String token=task.getResult().getToken();
                    Log.i("token",token);

                }
            }
        });


        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"attendance",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this,Attendence.class));


            }
        });

        marksheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Marksheet.class));

            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Event.class));

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreUserDetail.getInstance(MainActivity.this).logout();
                finish();
                startActivity(new Intent(MainActivity.this,login.class));

            }
        });

    }
}
