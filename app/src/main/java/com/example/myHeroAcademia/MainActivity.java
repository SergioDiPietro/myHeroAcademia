package com.example.myHeroAcademia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myHeroAcademia.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText heroIdBox;
    TextView urlDisplay;
    TextView resultDisplay;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        heroIdBox = (EditText)findViewById(R.id.hero_id);
        urlDisplay = (TextView) findViewById(R.id.url_display);
        resultDisplay = (TextView) findViewById(R.id.result_display);
    }
}