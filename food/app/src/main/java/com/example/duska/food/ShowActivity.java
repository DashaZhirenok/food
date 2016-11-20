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

public class ShowActivity extends AppCompatActivity implements View.OnClickListener{

    Button show,next;
    TextView textmenu, textshow;
    DBHelper dbHelper;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        textmenu = (TextView) findViewById(R.id.textmenu);
        textshow = (TextView) findViewById(R.id.textshow);
        show = (Button) findViewById(R.id.show);
        next = (Button) findViewById(R.id.next);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        show.setOnClickListener(this);
        next.setOnClickListener(OncNext);

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
                gotoadd.setClass(ShowActivity.this, MainActivity.class);
                startActivity(gotoadd);
                break;
            case R.id.menu_home:
                Intent gotohome = new Intent();
                gotohome.setClass(ShowActivity.this, HomeActivity.class);
                startActivity(gotohome);
                break;

            case R.id.help:
                Intent gotohelp = new Intent();
                gotohelp.setClass(ShowActivity.this, HelpActivity.class);
                startActivity(gotohelp);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener OncNext = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent gotonext = new Intent();
            gotonext.setClass(ShowActivity.this, ShowRecipeActivity.class);
            startActivity(gotonext);
        }
    };

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

}






