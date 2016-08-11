package com.example.retrofitsimple;

import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitsimple.entities.Student;
import com.example.retrofitsimple.network.NamesAdapter;
import com.example.retrofitsimple.network.RetrofitMagic;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MyDialogFragment.DialogListener{

    private static final String TAG = MainActivity.class.getSimpleName() + "TAG_";
    private RecyclerView mRecyclerView;
    private ArrayList<Student> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mList = RetrofitMagic.getStudents();

        NamesAdapter namesAdapter = new NamesAdapter(mList, this);

        mRecyclerView = (RecyclerView) findViewById(R.id.a_main_recycler);
        mRecyclerView.setAdapter(namesAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String pass) {
        Student student = mList.get(dialog.getArguments().getInt("position"));
        if(pass.equals(student.getPassword())){
            Toast.makeText(this, "Login successfully", Toast.LENGTH_LONG).show();
        }
        else{
            View view = findViewById(R.id.main_view);
            Snackbar.make(view, "Wrong password for " + dialog.getArguments().getString("name"), Snackbar.LENGTH_SHORT).show();
        }
    }
}
