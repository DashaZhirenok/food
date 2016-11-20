package com.example.duska.food;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class ShowRecipeActivity extends AppCompatActivity implements View.OnClickListener{

    Button show,back;
    TextView textmenu, textshow;
    DBHelper dbHelper;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe);

        textmenu = (TextView) findViewById(R.id.textmenu);
        textshow = (TextView) findViewById(R.id.textshow);
        show = (Button) findViewById(R.id.show);
        back = (Button) findViewById(R.id.back);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        back.setOnClickListener(OncBack);

        show.setOnClickListener(this);

        dbHelper = new DBHelper(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    };


    @Override //действия при нажатии на пункты меню

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id)
        {
            case R.id.menu_add:
                Intent gotoadd = new Intent();
                gotoadd.setClass(ShowRecipeActivity.this, MainActivity.class);
                startActivity(gotoadd);
                break;
            case R.id.menu_home:
                Intent gotohome = new Intent();
                gotohome.setClass(ShowRecipeActivity.this, HomeActivity.class);
                startActivity(gotohome);
                break;

            case R.id.help:
                Intent gotohelp = new Intent();
                gotohelp.setClass(ShowRecipeActivity.this, HelpActivity.class);
                startActivity(gotohelp);
                break;
        }


        return super.onOptionsItemSelected(item);
    }



    View.OnClickListener OncBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent gotoback = new Intent();
            gotoback.setClass(ShowRecipeActivity.this, ShowActivity.class);
            startActivity(gotoback);
        }
    };

    @Override
    public void onClick(View v) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // Делаем запрос
        Cursor cursor3 = database.query(DBHelper.TABLE_RECIPES, null, null, null, null, null, null);
        try {

            // Узнаем индекс каждого столбца
            int idColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_ID3);
            int nameofdishinrecipeColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_NAMEOFDISHINRECIPE);
            int recipeColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_RECIPE);

            // Проходим через все ряды
            while (cursor3.moveToNext()) {
                // Используем индекс для получения строки или числа
                int currentID = cursor3.getInt(idColumnIndex);
                String currentNameofdishinrecipe = cursor3.getString(nameofdishinrecipeColumnIndex);
                String currentRecipe = cursor3.getString(recipeColumnIndex);

                // Выводим значения каждого столбца
                textshow.append(("\n" + currentID + ": " +
                        currentNameofdishinrecipe + " (" +
                        currentRecipe + "). "));
            }
        }
        finally {
            // Всегда закрываем курсор после чтения
            cursor3.close();
        }

    }

}
