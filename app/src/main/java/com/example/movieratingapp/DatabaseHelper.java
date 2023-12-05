package com.example.movieratingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="MovieListApp.db";
    private static final String USER_TABLE_NAME="Users";
    private static final String MOVIE_TABLE_NAME="Movies";
    private static final String REVIEW_TABLE_NAME="Reviews";

    public DatabaseHelper (Context context)
    {
        //We will use this to create the database
        //it accepts the context, the name, factory (leave null), and version number
        //if your database becomes corrupt or the information in the database is wrong
        //change the version number
        //super is used to call the functionality of the base class SQLiteOpenHelper and
        //then executes the extended (DatabaseHelper)

        super(context,DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Create tables in the database
        //Execute the SQL statement on the database that was passed to the function OnCreated called db


        db.execSQL("CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME +
                " (username VARCHAR(255) PRIMARY KEY NOT NULL, password VARCHAR(255), admin BOOLEAN);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + MOVIE_TABLE_NAME +
                " (movieId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title VARCHAR(255), genre VARCHAR(255), releaseYear INTEGER, ageRating VARCHAR(8), description TEXT, trailerUrl VARCHAR(255));");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + REVIEW_TABLE_NAME +
                " (reviewId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, movieId INTEGER NOT NULL, userId INTEGER NOT NULL, comments TEXT, stars INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //This is used if we change the version number of the database.
        //delete the table if you upgrade the database (change the version number in the constructor)
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + MOVIE_TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + REVIEW_TABLE_NAME + ";");

        //create a new database once the old database has been deleted.
        onCreate(db);
    }

    public void initializeDB()
    {
        initializeUsers();
        initializeMovies();
    }

    public boolean initializeUsers()
    {
        if(numberOfRecordsInTable(USER_TABLE_NAME)==0)
        {
            //connect to the database
            //notice we are getting a writable database because we need to insert information into our database
            SQLiteDatabase db = this.getWritableDatabase();

            //user insert statements
            db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES('admin','admin','1');");
            db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES('mpalmer','gamerfart2004','1');");
            db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES('rbaker','jackstauber20','0');");
            db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES('jpalmer','enalover2254','0');");
            db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES('rravengard','shadowsamongus67','0');");

            db.close();

            return true;
        }
        else
        {
            return false;
        }

    }

    public boolean initializeMovies()
    {
        if(numberOfRecordsInTable(MOVIE_TABLE_NAME)==0)
        {
            //connect to the database
            //notice we are getting a writable database because we need to insert information into our database
            SQLiteDatabase db = this.getWritableDatabase();

            //movie insert statements
            db.execSQL("INSERT INTO " + MOVIE_TABLE_NAME + "(title, genre, releaseYear, ageRating, description, trailerUrl) VALUES('Default','Default','2023','PG','Default Movie','https://www.youtube.com/watch?v=dQw4w9WgXcQ');");
            db.execSQL("INSERT INTO " + MOVIE_TABLE_NAME + "(title, genre, releaseYear, ageRating, description, trailerUrl) VALUES('American Ultra','Action','2015','R','Small-town stoner Mike Howell spends most of his time getting high and writing a graphic novel about a superhero monkey. What Mike doesnt know is that he was trained by the CIA to be a lethal killing machine. When the agency targets him for termination, his former handler activates his latent skills, turning the mild-mannered slacker into a deadly weapon. Now, the utterly surprised Mike must use his newfound abilities to save himself and his girlfriend from getting smoked.','https://www.youtube.com/watch?v=q9QnZYevO7Y');");
            db.execSQL("INSERT INTO " + MOVIE_TABLE_NAME + "(title, genre, releaseYear, ageRating, description, trailerUrl) VALUES('Inception','Action','2010','PG-13','Dom Cobb (Leonardo DiCaprio) is a thief with the rare ability to enter peoples dreams and steal their secrets from their subconscious. His skill has made him a hot commodity in the world of corporate espionage but has also cost him everything he loves. Cobb gets a chance at redemption when he is offered a seemingly impossible task: Plant an idea in someones mind. If he succeeds, it will be the perfect crime, but a dangerous enemy anticipates Cobbs every move.','https://www.youtube.com/watch?v=YoHD9XEInc0');");
            db.execSQL("INSERT INTO " + MOVIE_TABLE_NAME + "(title, genre, releaseYear, ageRating, description, trailerUrl) VALUES('Donnie Darko','Thriller','2001','R','During the presidential election of 1988, a teenager named Donnie Darko sleepwalks out of his house one night and sees a giant, demonic-looking rabbit named Frank, who tells him the world will end in 28 days. When Donnie returns home, he finds that a jet engine has crashed into his bedroom. Is Donnie living in a parallel universe, is he suffering from mental illness - or will the world really end?','https://www.youtube.com/watch?v=rPeGaos7DB4');");

            db.close();

            return true;
        }
        else
        {
            return false;
        }

    }

    public int numberOfRecordsInTable(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);
        db.close();

        return numRows;
    }

    public boolean validUsernamePasswordCombo(String username, String password)
    {
        boolean goodUsernamePassword = false;
        if(usernameExists(username))
        {
            String getUserInfo = "Select password from " + USER_TABLE_NAME + " WHERE username = '" + username + "';";

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(getUserInfo, null);

            if(cursor != null)
            {
                cursor.moveToFirst();

                //no need for a loop.  Should only be one thing returned to us
                //we do not need to use getcolumindex because this should only return us one column because we are selecting password only in our query
                if(password.equals(cursor.getString(0).toString()))
                {
                    goodUsernamePassword = true;
                }
                else
                {
                    Log.d("BAD PASSWORD: ", "the password entered is not the correct password for that username");
                    goodUsernamePassword = false;
                }
            }

            db.close();
        }
        else
        {
            Log.d("BAD USERNAME: ", "the username entered was not found in the db");
            goodUsernamePassword = false;
        }


        return goodUsernamePassword;

    }

    public boolean usernameExists(String username)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        String checkUsername = "Select count(username) from " + USER_TABLE_NAME + " where username = '" + username + "';";
        Cursor cursor = db.rawQuery(checkUsername, null);
        //move cursor to the first thing because there should only be one thing returned.
        cursor.moveToFirst();
        //give getInt 0 for the first thing that is returned.  This should always return one thing because I am using the count function in sql
        //using getInt because count will return an int.
        int count = cursor.getInt(0);

        db.close();

        if(count != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @SuppressLint("Range")
    public ArrayList<Users> getAllUsers()
    {
        ArrayList<Users> listUsers = new ArrayList<Users>();

        //query to get all rows and attributes from our table
        //select * means get all attributes
        String selectQuery = "SELECT * FROM " + USER_TABLE_NAME + " ORDER BY username;";

        //get an instance of a readable database and store it in db
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query. Cursor will be used to cycle through the results
        Cursor cursor = db.rawQuery(selectQuery, null);

        String uname;
        String pword;
        boolean admin;

        //if there was something returned move the cursor to the beginning of the list
        if(cursor.moveToFirst())
        {
            do
            {
                uname = cursor.getString(cursor.getColumnIndex("username"));
                pword = cursor.getString(cursor.getColumnIndex("password"));
                int adminInt = cursor.getInt(cursor.getColumnIndex("admin"));

                // Convert the integer value to boolean
                admin = (adminInt == 1);

                //add the returned results to my list
                listUsers.add(new Users(uname, pword, admin));
            }
            while(cursor.moveToNext());
        }
        db.close();
        return listUsers;
    }

    @SuppressLint("Range")
    public ArrayList<Movies> getAllMovies()
    {
        ArrayList<Movies> listOfMovies = new ArrayList<Movies>();

        //query to get all rows and attributes from our table
        //select * means get all attributes
        String selectQuery = "SELECT * FROM " + MOVIE_TABLE_NAME + " ORDER BY movieId;";

        //get an instance of a readable database and store it in db
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query. Cursor will be used to cycle through the results
        Cursor cursor = db.rawQuery(selectQuery, null);

        Integer movieId;
        String title;
        String genre;
        Integer releaseYear;
        String ageRating;
        String description;
        String trailerUrl;

        //if there was something returned move the cursor to the beginning of the list
        if(cursor.moveToFirst())
        {
            do
            {
                movieId = cursor.getInt(cursor.getColumnIndex("movieId"));
                title = cursor.getString(cursor.getColumnIndex("title"));
                genre = cursor.getString(cursor.getColumnIndex("genre"));
                releaseYear = cursor.getInt(cursor.getColumnIndex("releaseYear"));
                ageRating = cursor.getString(cursor.getColumnIndex("ageRating"));
                description = cursor.getString(cursor.getColumnIndex("description"));
                trailerUrl = cursor.getString(cursor.getColumnIndex("trailerUrl"));

                //add to list
                listOfMovies.add(new Movies(movieId, title, genre, releaseYear, ageRating, description, trailerUrl));
            }
            while(cursor.moveToNext());
        }
        db.close();

        return listOfMovies;
    }

    @SuppressLint("Range")
    public ArrayList<Reviews> getAllReviews()
    {
        ArrayList<Reviews> listOfReviews = new ArrayList<Reviews>();

        //query to get all rows and attributes from our table
        //select * means get all attributes
        String selectQuery = "SELECT * FROM " + REVIEW_TABLE_NAME + " ORDER BY reviewId;";

        //get an instance of a readable database and store it in db
        SQLiteDatabase db = this.getReadableDatabase();

        //execute the query. Cursor will be used to cycle through the results
        Cursor cursor = db.rawQuery(selectQuery, null);

        Integer reviewId;
        Integer movieId;
        Integer userId;
        String comment;
        Integer rating;

        //if there was something returned move the cursor to the beginning of the list
        if(cursor.moveToFirst())
        {
            do
            {
                reviewId = cursor.getInt(cursor.getColumnIndex("reviewId"));
                movieId = cursor.getInt(cursor.getColumnIndex("movieId"));
                userId = cursor.getInt(cursor.getColumnIndex("userId"));
                comment = cursor.getString(cursor.getColumnIndex("comment"));
                rating = cursor.getInt(cursor.getColumnIndex("rating"));


                //add to list
                listOfReviews.add(new Reviews(reviewId, movieId, userId, comment, rating));
            }
            while(cursor.moveToNext());
        }
        db.close();

        return listOfReviews;
    }


    //USER CRUD====================================================================================
    public void addNewUser(Users u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES ('" + u.getUname() + "','" + u.getPword() + "','" + u.isAdmin() + "');");

        db.close();
    }

    public void deleteUser(String u)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //create our delete command
        db.execSQL("DELETE FROM " + USER_TABLE_NAME + " WHERE username = '" + u + "';");

        //close the database
        db.close();
    }

    public void updateUser(Users u)
    {
        //get writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create out update command
        //needs to look like this:
        //UPDATE users SET firstname = 'Zack' , lastname = 'Moore' WHERE username = 'zmoore';
        String updateCommand = "UPDATE " + USER_TABLE_NAME + " SET password = '" + u.getPword() + "' , admin = '" + u.isAdmin() + "' WHERE username = '" + u.getUname() + "';";

        db.execSQL(updateCommand);
        db.close();
    }

    //MOVIE CRUD===================================================================================



}
