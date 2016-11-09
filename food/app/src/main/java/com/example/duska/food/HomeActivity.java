package com.example.duska.food;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView textView;
    Button btn_help, btn_recipe, btn_addNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_help = (Button) findViewById(R.id.btn_help);
        btn_addNew = (Button) findViewById(R.id.btn_addNew);
        btn_recipe = (Button) findViewById(R.id.btn_recipe);

        btn_addNew.setOnClickListener(OncAll);
        btn_recipe.setOnClickListener(OncAll);
        btn_help.setOnClickListener(OncAll);

    }

    View.OnClickListener OncAll = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case (R.id.btn_recipe):
                    Intent gotorecipe = new Intent();
                    gotorecipe.setClass(HomeActivity.this, RecipeActivity.class);
                    startActivity(gotorecipe);
                break;

                case (R.id.btn_help):
                    Intent gotohelp = new Intent();
                    gotohelp.setClass(HomeActivity.this, HelpActivity.class);
                    startActivity(gotohelp);
                    break;

                case (R.id.btn_addNew):
                    Intent gotoadd = new Intent();
                    gotoadd.setClass(HomeActivity.this, MainActivity.class);
                    startActivity(gotoadd);

                    break;
            }

        }
    };
}
