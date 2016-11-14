package com.example.duska.food;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnRead, btnClear, btnDelete, btnRecipe;
    EditText etNameofdish, etMealtime, etIngredients1, etIngredients2, etIngredients3, etIngredients4, etIngredients5;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRecipe = (Button) findViewById(R.id.btnRecipe);
        btnRecipe.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear); //clear ALL!!
        btnClear.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);


        etNameofdish = (EditText) findViewById(R.id.etNameofdish);
        etMealtime = (EditText) findViewById(R.id.etMealtime);
        etIngredients1 =(EditText) findViewById(R.id.etIngredients1);
        etIngredients2 =(EditText) findViewById(R.id.etIngredients2);
        etIngredients3 =(EditText) findViewById(R.id.etIngredients3);
        etIngredients4 =(EditText) findViewById(R.id.etIngredients4);
        etIngredients5 =(EditText) findViewById(R.id.etIngredients5);

        dbHelper = new DBHelper(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    };


    @Override //действия при нажатии на пункты меню

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id)
        {
            case R.id.menu_show:
                Intent gotoshow = new Intent();
                gotoshow.setClass(MainActivity.this, ShowActivity.class);
                startActivity(gotoshow);
                break;
            case R.id.menu_home:
                Intent gotohome = new Intent();
                gotohome.setClass(MainActivity.this, HomeActivity.class);
                startActivity(gotohome);
                break;

            case R.id.help:
                Intent gotohelp = new Intent();
                gotohelp.setClass(MainActivity.this, HelpActivity.class);
                startActivity(gotohelp);
                break;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        String nameofdish = etNameofdish.getText().toString();
        String ingredients1 = etIngredients1.getText().toString();
        String ingredients2 = etIngredients2.getText().toString();
        String ingredients3 = etIngredients3.getText().toString();
        String ingredients4 = etIngredients4.getText().toString();
        String ingredients5 = etIngredients5.getText().toString();
        String mealtime = etMealtime.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        ContentValues contentValues2 = new ContentValues();


        switch (v.getId()) {

            case R.id.btnAdd: //добавление данных в таблицу
                contentValues.put(DBHelper.KEY_NAMEOFDISH, nameofdish);
                contentValues.put(DBHelper.KEY_MEALTIME, mealtime);
                contentValues2.put(DBHelper.KEY_NUMBEROFDISH, nameofdish);
                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients1);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients2);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients3);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients4);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients5);
                database.insert(DBHelper.TABLE_MENU, null, contentValues);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                break;

            case R.id.btnRead: //чтение данных в log
                Cursor cursor = database.query(DBHelper.TABLE_MENU, null, null, null, null, null, null);
                Cursor cursor2 = database.query(DBHelper.TABLE_LISTOFPRODUCTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameofdishIndex = cursor.getColumnIndex(DBHelper.KEY_NAMEOFDISH);
                    int mealtimeIndex = cursor.getColumnIndex(DBHelper.KEY_MEALTIME);
                    do {
                        Log.d("mLog", "-------------------1--------------" + "ID = " + cursor.getInt(idIndex) +
                                ", name of dish = " + cursor.getString(nameofdishIndex) +
                                ", mealtime = " + cursor.getString(mealtimeIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog", "0 rows");

                cursor.close();

                if (cursor2.moveToFirst()) {
                    int idIndex2 = cursor2.getColumnIndex(DBHelper.KEY_ID2);
                    int ingredient1Index = cursor2.getColumnIndex(DBHelper.KEY_INGREDIENT);
                    int numberofdishIndex = cursor2.getColumnIndex(DBHelper.KEY_NUMBEROFDISH);
                    do {
                        Log.d(
                                "mLog", "-------------------2--------------" +
                                        "ID = " + cursor2.getInt(idIndex2) +
                                        ", ingredients = " + cursor2.getString(ingredient1Index)
                                        + ", number of dish = " + cursor2.getString(numberofdishIndex));
                    } while (cursor2.moveToNext());
                } else
                    Log.d("mLog", "0 rows");

                cursor2.close();

                break;


            case R.id.btnDelete: // удаление какого-то конкретного блюда
                if (nameofdish.equalsIgnoreCase("")) {
                    break;
                }
                int updCount = database.delete(DBHelper.TABLE_MENU, DBHelper.KEY_NAMEOFDISH + " = ? ", new String[]{nameofdish});
                int updCount2 = database.delete(DBHelper.TABLE_LISTOFPRODUCTS, DBHelper.KEY_NUMBEROFDISH + " = ?", new String[]{nameofdish});
                Log.d("mLog", "deleted rows count = " + updCount);
                Log.d("mLog", "deleted rows count = " + updCount2);

            case R.id.btnRecipe: //переход в заполнение рецептов
                Intent gotorecipe = new Intent();
                gotorecipe.setClass(MainActivity.this, RecipeActivity.class);
                startActivity(gotorecipe);

                break;

            case R.id.btnClear: //полное удаление данных из таблицы
                database.delete(DBHelper.TABLE_MENU, null, null);
                database.delete(DBHelper.TABLE_LISTOFPRODUCTS, null, null);
                //database.delete(DBHelper.TABLE_RECIPES, null, null);
                break;

        }
        dbHelper.close();
    }
}


