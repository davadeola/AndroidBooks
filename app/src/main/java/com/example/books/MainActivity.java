package com.example.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView rvBooks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBooks = findViewById(R.id.rv_books);
        LinearLayoutManager booksLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvBooks.setLayoutManager(booksLayoutManager);

        try {
            URL bookUrl = ApiUtil.buildUrl("cooking");
            new BookQueryTask().execute(bookUrl);
        }catch (Exception e){
            Log.d("Error", e.toString());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_list_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        try {
            //generates the url
            URL bookUrl = ApiUtil.buildUrl(query);

            //executes the api call in the async task
            new BookQueryTask().execute(bookUrl);

        }catch (Exception e){
            Log.d("Error" , e.toString());
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    //create a thread to query the web service
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
//
//            if (result == null){
//
//            }else{
//
//            }

            ArrayList<Book> books = ApiUtil.getBooksFromJson(result);
           BooksAdapter adapter = new BooksAdapter(books);
           rvBooks.setAdapter(adapter);

        }
    }
}