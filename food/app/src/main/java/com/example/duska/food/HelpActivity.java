package com.example.duska.food;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    };

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id)
        {
            case R.id.menu_add:
                Intent gotoadd = new Intent();
                gotoadd.setClass(HelpActivity.this, MainActivity.class);
                startActivity(gotoadd);
                break;

            case R.id.menu_home:
                Intent gotohome = new Intent();
                gotohome.setClass(HelpActivity.this, HomeActivity.class);
                startActivity(gotohome);
                break;

            case R.id.menu_show:
                Intent gotoshow = new Intent();
                gotoshow.setClass(HelpActivity.this, ShowActivity.class);
                startActivity(gotoshow);
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}
