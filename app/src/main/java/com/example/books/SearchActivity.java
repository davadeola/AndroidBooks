package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;
import java.net.URL;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText etTitle = findViewById(R.id.etTitle);
        final EditText etAuthor = findViewById(R.id.etAuthor);
        final EditText etPublisher = findViewById(R.id.etPublisher);
        final EditText etIsbn = findViewById(R.id.etISBN);
        final Button button = findViewById(R.id.btnSearch);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String author = etAuthor.getText().toString().trim();
                String publisher = etPublisher.getText().toString().trim();
                String isbn = etIsbn.getText().toString().trim();

                if(title.isEmpty() && author.isEmpty() && publisher.isEmpty() && isbn.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please insert a message", Toast.LENGTH_LONG).show();
                }else {

                    URL querUrl = ApiUtil.buildUrl(title, author, publisher, isbn);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("Query", querUrl.toString());
                    startActivity(intent);
                }

            }
        });

    }
}