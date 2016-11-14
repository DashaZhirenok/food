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
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRead, btnClear, btnAdd, btnMain;
    TextView textView, text_of_recipe, name_of_dish;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnMain = (Button) findViewById(R.id.btnMain);

        btnAdd.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnMain.setOnClickListener(this);

        text_of_recipe = (TextView) findViewById(R.id.text_of_recipe);
        textView = (TextView) findViewById(R.id.textView);
        name_of_dish = (TextView) findViewById(R.id.name_of_dish);

        dbHelper = new DBHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_recipe, menu);
        return true;
    };

    @Override //действия при нажатии на пункты меню (верхний toolbar)
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id)
        {
            case R.id.menu_show:
                Intent gotoshow = new Intent();
                gotoshow.setClass(RecipeActivity.this, ShowActivity.class);
                startActivity(gotoshow);
                break;
            case R.id.menu_home:
                Intent gotohome = new Intent();
                gotohome.setClass(RecipeActivity.this, HomeActivity.class);
                startActivity(gotohome);
                break;

            case R.id.help:
                Intent gotohelp = new Intent();
                gotohelp.setClass(RecipeActivity.this, HelpActivity.class);
                startActivity(gotohelp);
                break;
        }


        return super.onOptionsItemSelected(item);
    };


    @Override
    public void onClick(View v) {

        String nameofdishinrecipe = name_of_dish.getText().toString();
        String recipe = text_of_recipe.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.btnAdd: //добавление данных в таблицу RECIPE
                contentValues.put(DBHelper.KEY_NAMEOFDISHINRECIPE, nameofdishinrecipe);
                contentValues.put(DBHelper.KEY_RECIPE, recipe);

                database.insert(DBHelper.TABLE_RECIPES, null, contentValues);

                break;

            case R.id.btnRead: //чтение данных в log
                Cursor cursor = database.query(DBHelper.TABLE_RECIPES, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameofdishinrecipeIndex = cursor.getColumnIndex(DBHelper.KEY_NAMEOFDISHINRECIPE);
                    int recipeIndex = cursor.getColumnIndex(DBHelper.KEY_RECIPE);
                    do {
                        Log.d("mLog", "-------------------3--------------" + "ID = " + cursor.getInt(idIndex) +
                                ", name of dish in recipe = " + cursor.getString(nameofdishinrecipeIndex) +
                                ", recipe = " + cursor.getString(recipeIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor.close();

                break;

            case R.id.btnClear: //удаление данных из всей таблицы
               // database.delete(DBHelper.TABLE_MENU, null, null);
               // database.delete(DBHelper.TABLE_LISTOFPRODUCTS, null, null);
                database.delete(DBHelper.TABLE_RECIPES, null, null);
                break;

            case R.id.btnMain: //кнопка перехода в главную забивку таблицы
                Intent gotomain = new Intent();
                gotomain.setClass(RecipeActivity.this, MainActivity.class);
                startActivity(gotomain);


        }
        dbHelper.close();
    }
}