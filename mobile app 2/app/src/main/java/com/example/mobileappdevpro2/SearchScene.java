package com.example.mobileappdevpro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class SearchScene extends AppCompatActivity {

    DataBase database = new DataBase(SearchScene.this);
    ListView SearchMovies;
    ArrayAdapter <String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_scene);

        maintask(); //running the main task
    }

    //----------------------------------------------------------------------------------------------
    public void maintask(){  //this is the search method
        SearchMovies = (ListView) findViewById(R.id.listviewMovies);
        ArrayList<movie> instance = database.GetData();
        ArrayList <String> Movies = new ArrayList<>();


        for (int i = 0; i < instance.size(); i++) {
            Movies.add(instance.get(i).getTitle().toString()); //copying the movie titles to the movie array
        }

        adapter = new ArrayAdapter<String>(SearchScene.this, android.R.layout.simple_list_item_1,Movies); //making a adapter class
        SearchMovies.setAdapter(adapter);


    }

    //----------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {   //this part of the programee was refrenced from https://youtu.be/H3JAy94UFw0 from Angga Risky youtube channel
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.searchMovie);
        SearchView searchv = (SearchView)item.getActionView();
        searchv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    public void tohome(View view) {
        Intent intent = new Intent(SearchScene.this, MainActivity.class);
        startActivity(intent);
    }
    //----------------------------------------------------------------------------------------------
}