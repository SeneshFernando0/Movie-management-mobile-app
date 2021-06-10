package com.example.mobileappdevpro2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import java.util.ArrayList;


public class DataBase extends SQLiteOpenHelper {


    public static final String MOVIE_TABLE = "movie_table";
    public static final String favourites_table = "favourites_table";

    public static final String MOVIE_TITLE = "MovieTitle";
    public static final String MOVIE_YEAR = "MovieYear";
    public static final String MOVIE_DIRECTOR = "MovieDirector";
    public static final String ACTORS = "Actors";
    public static final String RATING = "Rating";
    public static final String REVIEW = "Review";
    public static final String Favourite = "favourite";

    public DataBase(@Nullable Context context) {
        super(context, "movie.db", null, 1);
    }



    //----------------------------------------------------------------------------------------------
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createtable1 = "CREATE TABLE " + MOVIE_TABLE + "(" + MOVIE_TITLE + " TEXT , " + MOVIE_YEAR +
                " TEXT, " + MOVIE_DIRECTOR + " TEXT, " + ACTORS + " TEXT, " + RATING + " INT, "+ REVIEW + " TEXT "+" )"; //creating the movie table

        String createtable2 = "CREATE TABLE "+ favourites_table + "(" +Favourite+" TEXT )";  //creating the favourites table

        db.execSQL(createtable1);
        db.execSQL(createtable2);
    }
    //----------------------------------------------------------------------------------------------



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //----------------------------------------------------------------------------------------------
    public boolean AddToDataBase(String MovieTitle, String MovieYear, String MovieDirector, String Actors, String Rating, String Review) { //method to add data to database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MOVIE_DIRECTOR, MovieDirector);
        cv.put(MOVIE_TITLE, MovieTitle);
        cv.put(MOVIE_YEAR, MovieYear);
        cv.put(ACTORS, Actors);
        cv.put(RATING, Rating);
        cv.put(REVIEW, Review);

        long result = db.insert(MOVIE_TABLE, null, cv);
        return result != -1;  //returning a boolean value to check if the data hav been entered or not into the data base
    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    public ArrayList<movie> GetData() { //method to call for the data from the data base
        ArrayList<movie> data = new ArrayList<>(); //to store the data
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cus = db.rawQuery("select*from " + MOVIE_TABLE, null);//sql query t select all data in the table

        while (cus.moveToNext()) {
            String title = cus.getString(0);
            String year = cus.getString(1);
            String Director = cus.getString(2);
            String actors = cus.getString(3);
            String rating = cus.getString(4);
            String review = cus.getString(5);

            movie movie = new movie(title,year,Director,actors,rating,review); //calling the movie class and inisializing the data
            data.add(movie);
        }


        cus.close();
        db.close();
        return data;
    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    public ArrayList<favourites> AddtoFavourites(String Favourites){ //method to add the favourite movies to the favourites table
        //getting the string to be added through the prams
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Favourite, Favourites);

        db.insert(favourites_table, null, cv);
        return null;
    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    public ArrayList<favourites> getFavourites(){ //getting all favourite movies from the database
        ArrayList<favourites> Fdata = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cus = db.rawQuery("select*from " + favourites_table, null); //running the select all sql query to get all from the favourites table

        while (cus.moveToNext()) { //using a cursor and a while loop to iterate through the table
            String favouritess = cus.getString(0);

            favourites fav = new favourites(favouritess);
            Fdata.add(fav);//adding the data to a Arraylist with a class
        }
        cus.close();
        db.close();
        return Fdata;

    }
    //----------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------
    public void RemoveFromFavourites(String remove){ //removing favourite movies from the tables and getting the String from the prams which is what to be deleted

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + favourites_table+ " WHERE "+Favourite+"='"+remove+"'"); //running sql query to delete from favourites table
        db.close();
    }


    public void UpdateDataBase(String MovieTitle, String MovieYear, String MovieDirector, String Actors, String Rating, String Review){//to update the tables
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MOVIE_DIRECTOR, MovieDirector);
        cv.put(MOVIE_TITLE, MovieTitle);
        cv.put(MOVIE_YEAR, MovieYear);
        cv.put(ACTORS, Actors);
        cv.put(RATING, Rating);
        cv.put(REVIEW, Review);


        db.update(MOVIE_TABLE,cv,MOVIE_TITLE+" = ?",new String[]{ MovieTitle }); //updating the tables with the new data

    }
    //----------------------------------------------------------------------------------------------



}


