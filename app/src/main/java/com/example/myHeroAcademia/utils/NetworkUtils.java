package com.example.myHeroAcademia.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String BASE_URL = "https://akabab.github.io/superhero-api/api";
    final static String ALL_HEROES_PARAM = "/all.json";
    final static String SINGLE_HERO_PARAM = "id";
    final static String HERO_ID = "*.json";

    public static URL generateUrl() {
        Uri buildUri = Uri.parse(BASE_URL + ALL_HEROES_PARAM).buildUpon().build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL generateUrl(char heroID) {
        Uri buildUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(SINGLE_HERO_PARAM, HERO_ID.replace('*', heroID))
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream is = urlConnection.getInputStream();

        Scanner scan = new Scanner(is);
        scan.useDelimiter("\\A");
        try {
            if (scan.hasNext()) {
                return scan.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}