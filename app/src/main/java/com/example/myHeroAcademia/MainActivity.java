package com.example.myHeroAcademia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myHeroAcademia.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText heroIdBox;
    TextView urlDisplay;
    TextView resultDisplay;

    public class HeroesQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String heroSearchResolve = null;

            try {
                heroSearchResolve = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return heroSearchResolve;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null && !s.equals("")) {
                resultDisplay.setText(s);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.launch) {
            URL heroesApiUrl;
            if (heroIdBox.getText().toString().isEmpty()) {
                heroesApiUrl = NetworkUtils.generateUrl();
            } else {
                heroesApiUrl = NetworkUtils.generateUrl(heroIdBox.getText().toString().charAt(0));
            }
            urlDisplay.setText(heroesApiUrl.toString());

            new HeroesQueryTask().execute(heroesApiUrl);
        } else if (itemId == R.id.clear) {
            urlDisplay.setText("");
            resultDisplay.setText("");
        }
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