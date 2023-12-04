package com.example.movieratingapp;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        super(context,DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Create table in the database
        //Execute the SQL statement on the database that wass passed to the function OnCreated called db
        //This can be tricky becuase we have to write our SQL statements as strings


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

    public boolean initializeDB()
    {
        if(numberOfRowsInUser()==0 && numberOfRowsInMovie()==0 && numberOfRowsInReview()==0)
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

            //movie insert statements
            db.execSQL("INSERT INTO " + MOVIE_TABLE_NAME + "(title) VALUES('Default','Default','2023','PG','Default Movie','https://www.youtube.com/watch?v=dQw4w9WgXcQ');");
            db.execSQL("INSERT INTO " + MOVIE_TABLE_NAME + "(title) VALUES('American Ultra','Action','2015','R','Small-town stoner Mike Howell spends most of his time getting high and writing a graphic novel about a superhero monkey. What Mike doesnt know is that he was trained by the CIA to be a lethal killing machine. When the agency targets him for termination, his former handler activates his latent skills, turning the mild-mannered slacker into a deadly weapon. Now, the utterly surprised Mike must use his newfound abilities to save himself and his girlfriend from getting smoked.','https://www.youtube.com/watch?v=q9QnZYevO7Y');");
            db.execSQL("INSERT INTO " + MOVIE_TABLE_NAME + "(title) VALUES('Inception','Action','2010','PG-13','Dom Cobb (Leonardo DiCaprio) is a thief with the rare ability to enter peoples dreams and steal their secrets from their subconscious. His skill has made him a hot commodity in the world of corporate espionage but has also cost him everything he loves. Cobb gets a chance at redemption when he is offered a seemingly impossible task: Plant an idea in someones mind. If he succeeds, it will be the perfect crime, but a dangerous enemy anticipates Cobbs every move.','https://www.youtube.com/watch?v=YoHD9XEInc0');");
            db.execSQL("INSERT INTO " + MOVIE_TABLE_NAME + "(title) VALUES('Donnie Darko','Thriller','2001','R','During the presidential election of 1988, a teenager named Donnie Darko sleepwalks out of his house one night and sees a giant, demonic-looking rabbit named Frank, who tells him the world will end in 28 days. When Donnie returns home, he finds that a jet engine has crashed into his bedroom. Is Donnie living in a parallel universe, is he suffering from mental illness - or will the world really end?','https://www.youtube.com/watch?v=rPeGaos7DB4');");

            db.close();

            return true;
        }
        else
        {
            return false;
        }

    }

    public int numberOfRowsInUser()
    {
        //Look at the database we created
        //get a readable version
        SQLiteDatabase db =this.getReadableDatabase();
        //store the number of records in the table called TABLE_NAME
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USER_TABLE_NAME);
        //close the database
        db.close();

        return numRows;
    }

    public int numberOfRowsInMovie()
    {
        //Look at the database we created
        //get a readable version
        SQLiteDatabase db =this.getReadableDatabase();
        //store the number of records in the table called TABLE_NAME
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MOVIE_TABLE_NAME);
        //close the database
        db.close();

        return numRows;
    }

    public int numberOfRowsInReview()
    {
        //Look at the database we created
        //get a readable version
        SQLiteDatabase db =this.getReadableDatabase();
        //store the number of records in the table called TABLE_NAME
        int numRows = (int) DatabaseUtils.queryNumEntries(db, REVIEW_TABLE_NAME);
        //close the database
        db.close();

        return numRows;
    }

    public void addNewUser(Users u)
    {
        //get an instance of a writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //This line is a little complicated the sql statement should look as follows:
        //INSERT INTO users VALUES('zmoore','Zack','Moore');

        db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES ('" + u.getUname() + "','" + u.getPword() + "','" + u.isAdmin() + "');");
    }




}
