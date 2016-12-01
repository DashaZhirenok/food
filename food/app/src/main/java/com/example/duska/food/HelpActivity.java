package com.example.duska.food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(mActionBarToolbar);
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

            case R.id.menu_home:
                Intent gotohome = new Intent();
                gotohome.setClass(HelpActivity.this, HomeActivity.class);
                startActivity(gotohome);
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}
