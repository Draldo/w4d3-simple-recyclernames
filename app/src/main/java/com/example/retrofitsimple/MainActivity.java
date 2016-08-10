package com.example.retrofitsimple;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.retrofitsimple.entities.Student;
import com.example.retrofitsimple.network.NamesAdapter;
import com.example.retrofitsimple.network.RetrofitMagic;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        NamesAdapter namesAdapter = new NamesAdapter(RetrofitMagic.getStudents(), this);

        mRecyclerView = (RecyclerView) findViewById(R.id.a_main_recycler);
        mRecyclerView.setAdapter(namesAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
