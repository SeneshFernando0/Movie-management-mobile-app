package com.example.mobileappdevpro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class FavouriteMovieScene extends AppCompatActivity {

    DataBase database = new DataBase(FavouriteMovieScene.this);

    TableRow rows[];
    CheckBox tickboxes[];
    TextView textdata[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movie_scene);

        DisplayFavourites();
    }

    //----------------------------------------------------------------------------------------------
    public void DisplayFavourites(){

        TableLayout tablel = (TableLayout)findViewById(R.id.viewtl);
        ArrayList<favourites> instance = database.getFavourites();

        rows = new TableRow[instance.size()];
        tickboxes = new CheckBox[instance.size()];
        textdata = new TextView[instance.size()];


        //making checkboxes ,rows ,text views and adding to a array
        for(int i=0; i<instance.size(); i++){
            rows[i] = new TableRow(this);
            tickboxes[i] = new CheckBox(this);
            textdata[i] = new TextView(this);
            textdata[i].setTextSize(25);
        }


        //adding data to the textviews, tickboxes and adding them to a row adding the row to the table view
        int size = instance.size();
        for (int j =0; j < size; j++) {
            textdata[j].setText(instance.get(j).getFavourites());
            rows[j].addView(textdata[j]);
            rows[j].addView(tickboxes[j]);
            tablel.addView(rows[j]);
        }
    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    public void UpdateFavourites(View view) { //updating the favourites if edited
        for (int i = 0; i <tickboxes.length; i++) {
            if(tickboxes[i].isChecked()){
                database.RemoveFromFavourites(textdata[i].getText().toString());
            }
        }
    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    public void tohome(View view) { //to home page
        Intent intent = new Intent(FavouriteMovieScene.this, MainActivity.class);
        startActivity(intent);
    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    public void refresh(View view) { //to refresh after editing the data
        Intent intent = new Intent(FavouriteMovieScene.this, FavouriteMovieScene.class);
        startActivity(intent);
    }
    //----------------------------------------------------------------------------------------------
}