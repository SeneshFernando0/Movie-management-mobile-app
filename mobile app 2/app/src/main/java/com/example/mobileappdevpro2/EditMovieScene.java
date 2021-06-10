package com.example.mobileappdevpro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class EditMovieScene extends AppCompatActivity {

    DataBase database = new DataBase(EditMovieScene.this);

    static String titleSelected; //to store the String movie name to be used by the editmoviescene2
    Button TitleButton[]; //button array
    TableRow Trow[]; //table row array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie_scene);

        ButtonTitles();

    }

//--------------------------------------------------------------------------------------------------
    public void ButtonTitles(){
        ArrayList<movie> instance = database.GetData();
        TitleButton = new Button[instance.size()];
        Trow = new TableRow[instance.size()];

        TableLayout layoutmain = (TableLayout) findViewById(R.id.buttonlayout1);


        //to create buttons and adding into a array with custom sizes according to the movies present
        for(int i=0; i<instance.size(); i++){

            TitleButton[i] = new Button(this);
            Trow[i] = new TableRow(this);

            TitleButton[i].setHeight(70);
            TitleButton[i].setWidth(1200);

        }


        //adding the buttons a movie name and adding them to a row and adding the row to a table layout
        for (int j = 0; j < instance.size(); j++) {
            TitleButton[j].setText(instance.get(j).getTitle());
            Trow[j].addView(TitleButton[j]);
            layoutmain.addView(Trow[j]);
        }



        //if any button from the array is pressed the details of tht button movie is displayed in a new activity
        for (int i = 0; i < TitleButton.length; i++) {
            int finalI1 = i;
            TitleButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    titleSelected = TitleButton[finalI1].getText().toString(); //adding the String movie name to be used by the editmoviescene2

                    Intent intent = new Intent(EditMovieScene.this, EditMovieScenePart2.class); //opening a new activity to display the details of the the movie
                    startActivity(intent);

                }
            });
        }
    }
    //--------------------------------------------------------------------------------------------------


    //--------------------------------------------------------------------------------------------------
    public void tohome(View view) { //return to the main starting window
        Intent intent = new Intent(EditMovieScene.this, MainActivity.class);
        startActivity(intent);
    }
    //--------------------------------------------------------------------------------------------------
}