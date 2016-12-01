package com.example.duska.food;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnDelete, btnAdd2, btnDelete2;
    EditText etNameofdish, etMealtime, etCategory, etIngredients1, etIngredients2, etIngredients3, etIngredients4, etIngredients5;
    EditText etPrice1, etPrice2, etPrice3, etPrice4, etPrice5;
    TextView textView, text_of_recipe, name_of_dish;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> pages = new ArrayList<View>();
        View page = inflater.inflate(R.layout.activity_recipe, null);

        Toolbar mActionBarToolbar = (Toolbar) page.findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        text_of_recipe = (TextView) page.findViewById(R.id.text_of_recipe);
        textView = (TextView) page.findViewById(R.id.textView);
        name_of_dish = (TextView) page.findViewById(R.id.name_of_dish);


        btnAdd2 = (Button) page.findViewById(R.id.btnAdd2);
        btnDelete2 = (Button) page.findViewById(R.id.btnDelete2);
        btnAdd2.setOnClickListener(OncAll);
        btnDelete2.setOnClickListener(OncAll);
        dbHelper = new DBHelper(this);

        pages.add(page);

        //the second page
        page = inflater.inflate(R.layout.activity_main, null);
        Toolbar mActionBarToolbar2 = (Toolbar) page.findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar2);


        btnAdd = (Button) page.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnDelete = (Button) page.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);


        etNameofdish = (EditText) page.findViewById(R.id.etNameofdish);
        etMealtime = (EditText) page.findViewById(R.id.etMealtime);
        etCategory = (EditText) page.findViewById(R.id.etCategory);
        etPrice1 = (EditText) page.findViewById(R.id.etPrice1);
        etPrice2 = (EditText) page.findViewById(R.id.etPrice2);
        etPrice3 = (EditText) page.findViewById(R.id.etPrice3);
        etPrice4 = (EditText) page.findViewById(R.id.etPrice4);
        etPrice5 = (EditText) page.findViewById(R.id.etPrice5);
        etIngredients1 =(EditText) page.findViewById(R.id.etIngredients1);
        etIngredients2 =(EditText) page.findViewById(R.id.etIngredients2);
        etIngredients3 =(EditText) page.findViewById(R.id.etIngredients3);
        etIngredients4 =(EditText) page.findViewById(R.id.etIngredients4);
        etIngredients5 =(EditText) page.findViewById(R.id.etIngredients5);

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

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id)
        {

            case R.id.menu_home:
                Intent gotohome = new Intent();
                gotohome.setClass(MainActivity.this, HomeActivity.class);
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


        String nameofdish = etNameofdish.getText().toString();
        String mealtime = etMealtime.getText().toString();
        String category = etCategory.getText().toString();
        String ingredients1 = etIngredients1.getText().toString();
        String price1 = etPrice1.getText().toString();
        String price2 = etPrice2.getText().toString();
        String price3 = etPrice3.getText().toString();
        String price4 = etPrice4.getText().toString();
        String price5 = etPrice5.getText().toString();
        String ingredients2 = etIngredients2.getText().toString();
        String ingredients3 = etIngredients3.getText().toString();
        String ingredients4 = etIngredients4.getText().toString();
        String ingredients5 = etIngredients5.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        ContentValues contentValues2 = new ContentValues();


        switch (v.getId()) {

            case R.id.btnAdd: //добавление данных в таблицу
                contentValues.put(DBHelper.KEY_NAMEOFDISH, nameofdish);
                contentValues.put(DBHelper.KEY_MEALTIME, mealtime);
                contentValues.put(DBHelper.KEY_CATEGORY, category);
                contentValues2.put(DBHelper.KEY_NUMBEROFDISH, nameofdish);
                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients1);
                contentValues2.put(DBHelper.KEY_PRICE, price1);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients2);
                contentValues2.put(DBHelper.KEY_PRICE, price2);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients3);
                contentValues2.put(DBHelper.KEY_PRICE, price3);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients4);
                contentValues2.put(DBHelper.KEY_PRICE, price4);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients5);
                contentValues2.put(DBHelper.KEY_PRICE, price5);
                database.insert(DBHelper.TABLE_MENU, null, contentValues);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                break;


            case R.id.btnDelete: // удаление какого-то конкретного блюда
            {
                if (nameofdish.equalsIgnoreCase("")) {
                    break;
                }
                int updCount = database.delete(DBHelper.TABLE_MENU, DBHelper.KEY_NAMEOFDISH + " = ? ", new String[]{nameofdish});
                int updCount2 = database.delete(DBHelper.TABLE_LISTOFPRODUCTS, DBHelper.KEY_NUMBEROFDISH + " = ?", new String[]{nameofdish});
                Log.d("mLog", "deleted rows count = " + updCount);
                Log.d("mLog", "deleted rows count = " + updCount2);
            }

        }
        dbHelper.close();
    }

    View.OnClickListener OncAll = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nameofdishinrecipe = name_of_dish.getText().toString();
            String recipe = text_of_recipe.getText().toString();

            SQLiteDatabase database = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();


            switch (v.getId()) {

                case R.id.btnAdd2: //добавление данных в таблицу RECIPE
                    contentValues.put(DBHelper.KEY_NAMEOFDISHINRECIPE, nameofdishinrecipe);
                    contentValues.put(DBHelper.KEY_RECIPE, recipe);

                    database.insert(DBHelper.TABLE_RECIPES, null, contentValues);
                    break;


                case R.id.btnDelete: // удаление какого-то конкретного блюда
                    if (nameofdishinrecipe.equalsIgnoreCase("")) {
                        break;
                    }
                    int updCount = database.delete(DBHelper.TABLE_MENU, DBHelper.KEY_NAMEOFDISH + " = ? ", new String[]{nameofdishinrecipe});
                    int updCount2 = database.delete(DBHelper.TABLE_LISTOFPRODUCTS, DBHelper.KEY_NUMBEROFDISH + " = ?", new String[]{nameofdishinrecipe});
                    Log.d("mLog", "deleted rows count = " + updCount);
                    Log.d("mLog", "deleted rows count = " + updCount2);



            }
            dbHelper.close();
        }
        };

    }






