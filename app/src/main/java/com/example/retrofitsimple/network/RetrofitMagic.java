package com.example.retrofitsimple.network;

import com.example.retrofitsimple.entities.Student;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by evin on 8/10/16.
 */
public class RetrofitMagic {

    public static void main(String... args) {
//        ArrayList<Student> students = getStudents();
    }

    public static ArrayList<Student> getStudents() {
        //3. Creating Retrofit builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //4. Instantiating the interface via the Retrofit object
        NamesInterface namesInterface
                = retrofit.create(NamesInterface.class);

        //5. Setting up the method to be called from the interface
        Call<ArrayList<Student>> studentsCall
                = namesInterface.retrieveStudentsImages();

        ArrayList<Student> students = null;

        try {
            //6. Executing the Retrofit call
            students = studentsCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

}
