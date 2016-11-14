package com.example.duska.food;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener{

    Button show;
    TextView textshow;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        textshow = (TextView) findViewById(R.id.textshow);
        show = (Button) findViewById(R.id.show);

        show.setOnClickListener(this);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // Делаем запрос
        Cursor cursor3 = database.query(DBHelper.TABLE_MENU, null, null, null, null, null, null);
        try {
            //displayTextView.setText("Таблица содержит " + cursor3.getCount() + " гостей.\n\n");
            // displayTextView.append(DBHelper.KEY_ID + "-" +
            //       DBHelper.KEY_MEALTIME + "-" +
            //     DBHelper.KEY_NAMEOFDISH + "\n");

            // Узнаем индекс каждого столбца
            int idColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_ID);
            int nameColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_MEALTIME);
            int cityColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_NAMEOFDISH);

            // Проходим через все ряды
            while (cursor3.moveToNext()) {
                // Используем индекс для получения строки или числа
                int currentID = cursor3.getInt(idColumnIndex);
                String currentName = cursor3.getString(nameColumnIndex);
                String currentCity = cursor3.getString(cityColumnIndex);

                // Выводим значения каждого столбца
                textshow.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentCity + " - "));
            }
        }
        finally {
            // Всегда закрываем курсор после чтения
            cursor3.close();
        }

    }

}






