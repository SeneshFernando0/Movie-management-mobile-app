package com.example.mobileappdevpro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterMovieScene extends AppCompatActivity {

    DataBase database = new DataBase(RegisterMovieScene.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie_scene);
    }

    //----------------------------------------------------------------------------------------------
    public void SavetoSQL(View view) { //saving to sql database


        EditText Mtitle = (EditText) findViewById(R.id.editTextMovieTitle);
        EditText Myear = (EditText) findViewById(R.id.editTextMovieYear);
        EditText Mdirector = (EditText) findViewById(R.id.editTextmoviedirector);
        EditText Mactor = (EditText) findViewById(R.id.editTextActors);
        EditText Mrating = (EditText) findViewById(R.id.editTextrating);
        EditText Mreview = (EditText) findViewById(R.id.editTextReview);


        Boolean isinserted =database.AddToDataBase(Mtitle.getText().toString(),
                Myear.getText().toString(),Mdirector.getText().toString(),
                Mactor.getText().toString(), Mrating.getText().toString(),
                Mreview.getText().toString());

        if(isinserted == true){
            Toast.makeText(RegisterMovieScene.this,"Data is inserted",Toast.LENGTH_LONG).show(); //displaying toast message if inserted
        }
        else{
            Toast.makeText(RegisterMovieScene.this,"Data is not inserted error",Toast.LENGTH_LONG).show();//displaying toast message if error
        }
    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    public void ToHome(View view) { //to home page
        Intent intent = new Intent(RegisterMovieScene.this, MainActivity.class);
        startActivity(intent);
    }
    //----------------------------------------------------------------------------------------------
}