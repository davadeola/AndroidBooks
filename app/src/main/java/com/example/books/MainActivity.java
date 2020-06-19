package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            URL bookUrl = ApiUtil.buildUrl("cooking");
            new BookQueryTask().execute(bookUrl);
        }catch (Exception e){
            Log.d("Error", e.toString());
        }



    }

    public class BookQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searhUrl = urls[0];
            String result = null;

            try {
                result = ApiUtil.getJson(searhUrl);

            } catch (IOException e) {
                Log.e("Error", e.getMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tvResult = findViewById(R.id.tvResult);
            tvResult.setText(result);
        }
    }
}