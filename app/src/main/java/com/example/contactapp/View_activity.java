package com.example.contactapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.DragAndDropPermissions;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class View_activity extends AppCompatActivity {
    ListView listView;
    Cursor cursor;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    DataProvider dataProvider;
    DataListAdapter dataListAdapter;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_activity);
        listView = findViewById(R.id.listview);
        dataListAdapter = new DataListAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(dataListAdapter);
        userDbHelper = new UserDbHelper(getApplicationContext());
        sqLiteDatabase = userDbHelper.getReadableDatabase();
        cursor = userDbHelper.getinformation(sqLiteDatabase);


        if (cursor.moveToFirst()) {
            do {
                String name, mobile, email;
                name = cursor.getString(0);
                mobile = cursor.getString(1);
                email = cursor.getString(2);
                dataProvider = new DataProvider(name, mobile, email);
                dataListAdapter.add(dataProvider);
                // Toast.makeText(getApplicationContext(),name+" "+mobile+" "+email,Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());

        } else {

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.tv1) {
            Intent i = new Intent(View_activity.this, Rating.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.feedback) {
            Intent i = new Intent(View_activity.this, Feedback.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);

    }



}