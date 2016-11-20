package com.example.duska.food;

/**
 * Created by Duska on 02.11.2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "foodDb";
    public static final String TABLE_MENU = "food";
    public static final String TABLE_LISTOFPRODUCTS="listofproducts";
    public static final String TABLE_RECIPES = "recipes";

            //the first table
    public static final String KEY_ID = "_id";
    public static final String KEY_NAMEOFDISH = "nameofdish";
    public static final String KEY_MEALTIME = "mealtime";
    public static final String KEY_CATEGORY = "category";


            //the second table
    public static final String KEY_ID2 = "_id";
    public static final String KEY_INGREDIENT = "ingredient";
    public static final String KEY_NUMBEROFDISH = "numberofdish";
    public static final String KEY_PRICE = "price";

          //the third table
    public static final String KEY_ID3 = "_id";
    public static final String KEY_NAMEOFDISHINRECIPE = "nameofdishinrecipe";
    public static final String KEY_RECIPE = "recipe";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_MENU + "(" + KEY_ID
                + " integer primary key," + KEY_NAMEOFDISH + " text," + KEY_MEALTIME + " text," + KEY_CATEGORY + " text"  + ")"); //CREATE THE FIRST TABLE

        db.execSQL("create table " + TABLE_LISTOFPRODUCTS + "(" + KEY_ID2
                + " integer primary key," + KEY_INGREDIENT + " text," + KEY_NUMBEROFDISH + " text," + KEY_PRICE + " text" + ")"); //CREATE THE FIRST TABLE

        db.execSQL("create table " + TABLE_RECIPES + "(" + KEY_ID3
                + " integer primary key," + KEY_NAMEOFDISHINRECIPE + " text," + KEY_RECIPE + " text"  + ")"); //CREATE THE FIRST TABLE

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_MENU + TABLE_LISTOFPRODUCTS + TABLE_RECIPES);

        onCreate(db);

    }
}
