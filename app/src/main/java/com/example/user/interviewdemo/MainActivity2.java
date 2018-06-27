package com.example.user.interviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View.*;
import android.view.View;
import android.content.*;

public class MainActivity2 extends AppCompatActivity {
    private  TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       t1 = (TextView)findViewById(R.id.textv1);
       t2 = (TextView)findViewById(R.id.textv2);
       t3 = (TextView)findViewById(R.id.textv3);

   Bundle b = (Bundle) getIntent().getExtras();
  t1.setText(b.getString("name"));
  t2.setText(b.getString("email"));
  t3.setText(b.getString("phone"));

    }
    }
