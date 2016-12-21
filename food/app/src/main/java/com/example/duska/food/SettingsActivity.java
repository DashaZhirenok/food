package com.example.duska.food;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_readLog, btn_deleteAll;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar mActionBarToolbar2 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar2);

        btn_readLog = (Button) findViewById(R.id.btn_readLog);
        btn_readLog.setOnClickListener(this);

        btn_deleteAll = (Button) findViewById(R.id.btn_deleteAll);
        btn_deleteAll.setOnClickListener(this);

        dbHelper = new DBHelper(this);


    }




    @Override
    public void onClick(View v) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        switch (v.getId())
        {
            case R.id.btn_deleteAll: //полное удаление данных из таблицы
                database.delete(DBHelper.TABLE_MENU, null, null);
                database.delete(DBHelper.TABLE_LISTOFPRODUCTS, null, null);
                database.delete(DBHelper.TABLE_RECIPES, null, null);
                break;

            case R.id.btn_readLog: //чтение данных в log только из таблиц без рецептов
                Cursor cursor = database.query(DBHelper.TABLE_MENU, null, null, null, null, null, null);
                Cursor cursor2 = database.query(DBHelper.TABLE_LISTOFPRODUCTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameofdishIndex = cursor.getColumnIndex(DBHelper.KEY_NAMEOFDISH);
                    int mealtimeIndex = cursor.getColumnIndex(DBHelper.KEY_MEALTIME);
                   // int categoryIndex = cursor.getColumnIndex(DBHelper.KEY_CATEGORY);
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


        }

    }
}
