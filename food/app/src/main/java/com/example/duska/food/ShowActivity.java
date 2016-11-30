package com.example.duska.food;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener{

    Button show, show2;
    TextView textmenu, textmenu2, textshow, textshow2;
    DBHelper dbHelper;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> pages = new ArrayList<View>();

        //the first page
        View page = inflater.inflate(R.layout.activity_show, null);
        textmenu = (TextView) page.findViewById(R.id.textmenu);
        textshow = (TextView) page.findViewById(R.id.textshow);
        show = (Button) page.findViewById(R.id.show);
        scrollView = (ScrollView) page.findViewById(R.id.scrollView);

        show.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        pages.add(page);


          //the second page
        page = inflater.inflate(R.layout.activity_show_recipe, null);
        show2 = (Button) page.findViewById(R.id.show2);
        textmenu2 = (TextView) page.findViewById(R.id.textmenu2);
        textshow2 = (TextView) page.findViewById(R.id.textshow2);

        show2.setOnClickListener(OncShowrecipe);
        dbHelper = new DBHelper(this);

        pages.add(page);


        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(pages);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);

        setContentView(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    };


    @Override //действия при нажатии на пункты меню

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id)
        {

            case R.id.menu_home:
                Intent gotohome = new Intent();
                gotohome.setClass(ShowActivity.this, HomeActivity.class);
                startActivity(gotohome);
                break;

            case R.id.help:
                Toast.makeText(getApplicationContext(),
                        "Please, scroll left or right",
                        Toast.LENGTH_SHORT).show();

                break;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // Делаем запрос
        Cursor cursor3 = database.query(DBHelper.TABLE_MENU, null, null, null, null, null, null);
        try {
           //  textmenu.setText("List of menu\n");
            /// textmenu.append(DBHelper.KEY_NAMEOFDISH + ", " +
               //      DBHelper.KEY_MEALTIME + ", " +
                 //    DBHelper.KEY_CATEGORY + "\n");

            // Узнаем индекс каждого столбца
            int idColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_ID);
            int MealtimeColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_NAMEOFDISH);
            int NameofdishColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_MEALTIME);
            int CategoryColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_CATEGORY);

            // Проходим через все ряды
            while (cursor3.moveToNext()) {
                // Используем индекс для получения строки или числа
                int currentID = cursor3.getInt(idColumnIndex);
                String currentNameofdish = cursor3.getString(NameofdishColumnIndex);
                String currentMealtime = cursor3.getString(MealtimeColumnIndex);
                String currentCategory = cursor3.getString(CategoryColumnIndex);

                // Выводим значения каждого столбца
                textshow.append(("\n" + currentID + ": " +
                        currentNameofdish + " (" +
                        currentMealtime + ", " +
                        currentCategory + "). "));
            }
        }
        finally {
            // Всегда закрываем курсор после чтения
            cursor3.close();
        }

    }

    View.OnClickListener OncShowrecipe = new View.OnClickListener() {
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
                    textshow2.append(("\n" + currentID + ": " +
                            currentNameofdishinrecipe + " (" +
                            currentRecipe + "). "));
                }
            }
            finally {
                // Всегда закрываем курсор после чтения
                cursor3.close();
            }

        }


    };

}






