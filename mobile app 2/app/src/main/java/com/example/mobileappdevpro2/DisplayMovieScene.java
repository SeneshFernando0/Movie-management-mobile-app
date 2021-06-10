package com.example.mobileappdevpro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;



public class DisplayMovieScene extends AppCompatActivity {

    DataBase database = new DataBase(DisplayMovieScene.this); //making a instance if the database helper class

    TableRow rows[]; //rows array
    CheckBox tickboxes[]; //tickboxes array
    TextView textdata[]; //text view array

    int instancecount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie_scene);



        DisplayData();
    }


    //----------------------------------------------------------------------------------------------
    public void DisplayData(){ //

        TableLayout tl = (TableLayout)findViewById(R.id.table);

        ArrayList<movie> instance = database.GetData();

        instancecount = instance.size()+0;


        rows = new TableRow[instance.size()];
        tickboxes = new CheckBox[instance.size()];
        textdata = new TextView[instance.size()];


        //setting sizes for the rows, buttons and text sizes
        for(int i=0; i<instance.size(); i++){

            rows[i] = new TableRow(this);
            tickboxes[i] = new CheckBox(this);
            textdata[i] = new TextView(this);
            textdata[i].setTextSize(22);
        }


        //adding the rows buttons with texts to the view
        int size = instance.size();
        for (int j =0; j < size; j++) {

            textdata[j].setText(instance.get(j).getTitle());
            rows[j].addView(textdata[j]);
            rows[j].addView(tickboxes[j]);
            tl.addView(rows[j]);
        }
    }
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    public void AddtoFavourites(View view) { //adding the data with a ticked box to the favourites list

        String temp;
        for (int i = 0; i < instancecount ; i++) { //adding to database if the boxes are ticked
            if(tickboxes[i].isChecked()){
                temp = textdata[i].getText().toString();
                database.AddtoFavourites(temp);
            }
        }

    }
    //----------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------
    public void tohome(View view) { //gong to main home activity
        Intent intent = new Intent(DisplayMovieScene.this, MainActivity.class);
        startActivity(intent);
    }
    //----------------------------------------------------------------------------------------------

}