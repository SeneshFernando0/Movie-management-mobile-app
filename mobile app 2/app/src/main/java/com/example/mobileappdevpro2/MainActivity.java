package com.example.mobileappdevpro2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    //calling the register movie activity-----------------------------------------------------------
    public void toRegisterMovie(View view) {
        Intent intent =new Intent(MainActivity.this,RegisterMovieScene.class);
        startActivity(intent);
    }


    //calling the display movie activity------------------------------------------------------------
    public void toDisplayMovie(View view) {
        Intent intent =new Intent(MainActivity.this,DisplayMovieScene.class);
        startActivity(intent);
    }


    //calling the favourite movie activity----------------------------------------------------------
    public void toFavourites(View view) {
        Intent intent =new Intent(MainActivity.this,FavouriteMovieScene.class);
        startActivity(intent);
    }


    //calling the edit movie activity---------------------------------------------------------------
    public void toEnditMovie(View view) {
        Intent intent =new Intent(MainActivity.this,EditMovieScene.class);
        startActivity(intent);
    }


    //calling the search movie activity-------------------------------------------------------------
    public void toSearchAndRating(View view) {
        Intent intent =new Intent(MainActivity.this,SearchScene.class);
        startActivity(intent);
    }
}