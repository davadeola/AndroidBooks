package com.example.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder>{

    ArrayList<Book> books;

    public BooksAdapter(ArrayList<Book> books){
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false);

        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bind(book);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;TextView tvAuthors;TextView tvDate;TextView tvPublisher;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthors = itemView.findViewById(R.id.tvAuthors);
            tvDate = itemView.findViewById(R.id.tvPublishDate);
            tvPublisher = itemView.findViewById(R.id.tvPublisher);
        }

        public void bind(Book book){
            tvTitle.setText(book.title);
            String auth = "";
            int i = 0;
             for (String author:book.authors){
                 auth +=author;
                 i++;
                 if (i<book.authors.length){
                     auth+= ", ";
                 }
             }

             tvAuthors.setText(auth);
             tvDate.setText(book.publishedDate);
             tvPublisher.setText(book.publisher);
        }


    }
}
