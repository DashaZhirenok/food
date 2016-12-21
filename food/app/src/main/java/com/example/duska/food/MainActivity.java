package com.example.duska.food;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd, btnDelete, button, show;
    EditText etNameofdish, etMealtime, etCategory, etIngredients1, etIngredients2, etIngredients3, etIngredients4, etIngredients5;
    EditText etPrice1, etPrice2, etPrice3, etPrice4, etPrice5;
    TextView text_of_recipe, textmenu, textshow;
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

        show.setOnClickListener(OncShow);

        dbHelper = new DBHelper(this);

        pages.add(page);

        //the second page
        page = inflater.inflate(R.layout.activity_main, null);

        //
        btnAdd = (Button) page.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnDelete = (Button) page.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);

        button = (Button) page.findViewById(R.id.button);
        button.setOnClickListener(this);

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
        text_of_recipe = (TextView) page.findViewById(R.id.text_of_recipe);
        dbHelper = new DBHelper(this);

        pages.add(page);

        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(pages);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);

        setContentView(viewPager);

    }

    @Override
    public void onClick(View v) {

        if (TextUtils.isEmpty(etNameofdish.getText().toString())) // если поле Name_of_dish - пустое
        {
                Toast.makeText(getApplicationContext(),
                        "Please, fill in the field 'Name of dish'",
                        Toast.LENGTH_SHORT).show();
            return;
        }


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
        String recipe = text_of_recipe.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        ContentValues contentValues2 = new ContentValues();
        ContentValues contentValues3 = new ContentValues();


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

                contentValues3.put(DBHelper.KEY_RECIPE, recipe);
                contentValues3.put(DBHelper.KEY_NAMEOFDISHINRECIPE, nameofdish);

                database.insert(DBHelper.TABLE_MENU, null, contentValues);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);
                database.insert(DBHelper.TABLE_RECIPES, null, contentValues3);

                                break;


            case R.id.btnDelete: // удаление какого-то конкретного блюда
            {
                if (nameofdish.equalsIgnoreCase("")) {
                    break;
                }
                int updCount = database.delete(DBHelper.TABLE_MENU, DBHelper.KEY_NAMEOFDISH + " = ? ", new String[]{nameofdish});
                int updCount2 = database.delete(DBHelper.TABLE_LISTOFPRODUCTS, DBHelper.KEY_NUMBEROFDISH + " = ?", new String[]{nameofdish});
                int updCount3 = database.delete(DBHelper.TABLE_RECIPES, DBHelper.KEY_NAMEOFDISHINRECIPE + " = ?", new String[]{nameofdish});
                Log.d("mLog", "deleted rows count = " + updCount);
                Log.d("mLog", "deleted rows count = " + updCount2);
                Log.d("mLog", "deleted rows count = " + updCount3);
            }
            case R.id.button: // удаление полей
            {
               etNameofdish.setText("");
               etMealtime.setText("");
               etCategory.setText("");
               etIngredients1.setText("");
               etIngredients2.setText("");
               etIngredients3.setText("");
               etIngredients4.setText("");
               etIngredients5.setText("");
                etPrice1.setText("");
                etPrice2.setText("");
                etPrice3.setText("");
                etPrice4.setText("");
                etPrice5.setText("");
                text_of_recipe.setText("");
            }

        }
        dbHelper.close();
    }

    View.OnClickListener OncShow = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!TextUtils.isEmpty(textshow.getText().toString())) //проверка условия на то, что уже textshow не пустые
            {
                return;
            }

            SQLiteDatabase database = dbHelper.getWritableDatabase();

            // Делаем запрос
            Cursor cursor3 = database.query(DBHelper.TABLE_MENU, null, null, null, null, null, null);
            Cursor cursor4 = database.query(DBHelper.TABLE_LISTOFPRODUCTS, null,null, null, null, null,null);
            Cursor cursor5 = database.query(DBHelper.TABLE_RECIPES, null,null, null, null, null,null);
            try {
                //  textmenu.setText("List of menu\n");
                /// textmenu.append(DBHelper.KEY_NAMEOFDISH + ", " +
                //      DBHelper.KEY_MEALTIME + ", " +
                //    DBHelper.KEY_CATEGORY + "\n");

                // Узнаем индекс каждого столбца
                int idColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_ID);
                int IngredientColumnIndex = cursor4.getColumnIndex(DBHelper.KEY_INGREDIENT);
                int Ingredient2ColumnIndex = cursor4.getColumnIndex(DBHelper.KEY_INGREDIENT);
                int Ingredient3ColumnIndex = cursor4.getColumnIndex(DBHelper.KEY_INGREDIENT);
                int Ingredient4ColumnIndex = cursor4.getColumnIndex(DBHelper.KEY_INGREDIENT);
                int Ingredient5ColumnIndex = cursor4.getColumnIndex(DBHelper.KEY_INGREDIENT);

                int id2ColumnIndex = cursor4.getColumnIndex(DBHelper.KEY_ID2);
                int MealtimeColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_MEALTIME);
                int NameofdishColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_NAMEOFDISH);
                int CategoryColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_CATEGORY);

                int RecipeColumnIndex = cursor5.getColumnIndex(DBHelper.KEY_RECIPE);


                // Проходим через все ряды
                while (cursor3.moveToNext() && cursor4.moveToNext() & cursor5.moveToNext()) {
                    // Используем индекс для получения строки или числа
                    int currentID2 = cursor4.getInt(id2ColumnIndex);
                    int currentID = cursor3.getInt(idColumnIndex);
                    String currentNameofdish = cursor3.getString(NameofdishColumnIndex);
                    String currentMealtime = cursor3.getString(MealtimeColumnIndex);
                    String currentCategory = cursor3.getString(CategoryColumnIndex);

                    String currentIngredient = cursor4.getString(IngredientColumnIndex);
                    cursor4.moveToNext();
                    String currentIngredient2 = cursor4.getString(Ingredient2ColumnIndex);
                    cursor4.moveToNext();
                    String currentIngredient3 = cursor4.getString(Ingredient3ColumnIndex);
                    cursor4.moveToNext();
                    String currentIngredient4 = cursor4.getString(Ingredient4ColumnIndex);
                    cursor4.moveToNext();
                    String currentIngredient5 = cursor4.getString(Ingredient5ColumnIndex);

                    String currentRecipe = cursor5.getString(RecipeColumnIndex);

                    // Выводим значения каждого столбца
                    textshow.append(("\n"+ "The dish " + currentID + ": " + "\n" +
                            currentNameofdish + " (" +
                            currentMealtime + ", " +
                            currentCategory + "). "));

                    if (TextUtils.isEmpty(currentIngredient)) //проверка условия на то, что ingredient существует
                    {
                        return;
                    }else
                    {
                        textshow.append(("\n"  + "Ingredients: " + "\n" + "1. " +
                                currentIngredient + "\n"));
                    }

                    if (TextUtils.isEmpty(currentIngredient2)) //проверка условия на то, что ingredient существует
                    {
                        return;
                    }else
                    {
                        textshow.append(("2. " + currentIngredient2 + "\n"));
                    }
                    if (TextUtils.isEmpty(currentIngredient3)) //проверка условия на то, что ingredient существует
                    {
                        return;
                    }else
                    {
                        textshow.append(("3. " + currentIngredient3 + "\n"));
                    }
                    if (TextUtils.isEmpty(currentIngredient4)) //проверка условия на то, что ingredient существует
                    {
                        return;
                    }else
                    {
                        textshow.append(("4. " + currentIngredient4 + "\n"));
                    }
                    if (TextUtils.isEmpty(currentIngredient5)) //проверка условия на то, что ingredient существует
                    {
                        return;
                    }else
                    {
                        textshow.append(("5. " + currentIngredient5 + "\n"));
                    }

                    textshow.append(("\n"));

                    if (TextUtils.isEmpty(currentRecipe)) //проверка условия на то, что recipe существует
                    {
                        textshow.append(("\n"+"____________________________"));
                        return;
                    }else
                    {
                        textshow.append(("Text of recipe: " + "\n" + currentRecipe));
                    }
                    textshow.append(("\n"+"____________________________"));

                }
            }
            finally {
                // Всегда закрываем курсор после чтения
                cursor3.close();
                cursor4.close();
            }

        }
    };




    }






