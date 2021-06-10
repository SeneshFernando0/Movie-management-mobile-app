package com.example.mobileappdevpro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class EditMovieScenePart2 extends AppCompatActivity {



    DataBase database = new DataBase(EditMovieScenePart2.this);


    String Mtitle;
    String Myear;
    String Mdirector;
    String Mactor;
    Float Mrating;
    String Mrate;
    String Mreview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie_scene_part2);

        MainDisplay();//calling the main task
        AddtoFav();
    }

    //----------------------------------------------------------------------------------------------
    public void MainDisplay(){ //main task
        //calling the xml items
        EditText textTitle = (EditText) findViewById(R.id.editTextMovieTitle);
        EditText textDirector = (EditText) findViewById(R.id.editTextmoviedirector);
        RatingBar textRating= (RatingBar) findViewById(R.id.ratingBar);
        EditText textReview = (EditText) findViewById(R.id.editTextReview);
        EditText textActor = (EditText) findViewById(R.id.editTextActors);
        EditText textYear = (EditText) findViewById(R.id.editTextMovieYear);
        CheckBox FavBox = (CheckBox) findViewById(R.id.favCheckbox);

        ArrayList<movie> instance = database.GetData();
        ArrayList<favourites> instance2 = database.getFavourites();

        EditMovieScene ed = new EditMovieScene();



        //this loop is there to get the data from the table and update the current view with the updated data
        for (int i = 0; i < instance.size(); i++) {


            if (instance.get(i).getTitle().equals(ed.titleSelected)) { //checking if the button pressed is showing the right data
                Mtitle = instance.get(i).getTitle();
                Mdirector = instance.get(i).getDirector();
                Mrate = instance.get(i).getRating();
                Mreview = instance.get(i).getReview();
                Mactor = instance.get(i).getActors();
                Myear = instance.get(i).getYear();
            }

        }



        for (int i = 0; i < instance2.size(); i++) { //if the data base has favourite and if the user pressed button is of the same title set the tick boxes to ticked
            if(Mtitle.equals(instance2.get(i).getFavourites())){
                FavBox.setChecked(true);
            }
        }



        //setting the text to edit text boxes so the user can edit the previous loaded data
        Mrating = Float.parseFloat(Mrate);
        Log.d("rate", "MainDisplay: "+Mrating);
        textRating.setRating((float)Mrating);
        textTitle.setText(Mtitle, TextView.BufferType.EDITABLE);
        textDirector.setText(Mdirector, TextView.BufferType.EDITABLE);
        textReview.setText(Mreview, TextView.BufferType.EDITABLE);
        textActor.setText(Mactor, TextView.BufferType.EDITABLE);
        textYear.setText(Myear, TextView.BufferType.EDITABLE);


    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    public void UpdateButton(View view) { //button to update the edited data

        //calling and inilizing the items
        EditText textTitle = (EditText) findViewById(R.id.editTextMovieTitle);
        EditText textDirector = (EditText) findViewById(R.id.editTextmoviedirector);
        RatingBar textRating= (RatingBar) findViewById(R.id.ratingBar);
        EditText textReview = (EditText) findViewById(R.id.editTextReview);
        EditText textActor = (EditText) findViewById(R.id.editTextActors);
        EditText textYear = (EditText) findViewById(R.id.editTextMovieYear);

        //replacing the variables with the updated values
        Mtitle = textTitle.getText().toString();
        Myear= textYear.getText().toString();
        Mdirector = textDirector.getText().toString();
        Mactor = textActor.getText().toString();
        Mrating = textRating.getRating();
        Mreview = textReview.getText().toString();


        database.UpdateDataBase(Mtitle,Myear,Mdirector,Mactor,Mrating.toString(),Mreview);//adding the updated values to the data base table
    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    public void tohome(View view) { //return to the previous page
        Intent intent = new Intent(EditMovieScenePart2.this, EditMovieScene.class);
        startActivity(intent);
    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    public void AddtoFav() { //adding the data to favourites table if the the checkbox is ticked

        CheckBox FavBox = (CheckBox) findViewById(R.id.favCheckbox);


        FavBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FavBox.isChecked()){
                    database.AddtoFavourites(Mtitle); //adding to data base if the box is ticked
                }

                if(!FavBox.isChecked()){
                    database.RemoveFromFavourites(Mtitle); //removing from the tables if the tick box is unchecked
                }
            }
        });

    }
    //----------------------------------------------------------------------------------------------
}